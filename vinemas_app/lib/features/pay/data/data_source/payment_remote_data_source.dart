// ignore_for_file: public_member_api_docs, sort_constructors_first
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:dio/dio.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter_stripe/flutter_stripe.dart';
import 'package:vinemas_app/core/config/app_url.dart';
import 'package:vinemas_app/core/service/logger_service.dart';
import 'package:vinemas_app/features/pay/data/model/payment_model.dart';
import 'package:vinemas_app/features/pay/domain/entity/payment.dart';
import 'package:vinemas_app/features/pay/domain/enum/pay_enum.dart';
import 'package:vinemas_app/features/ticket/domain/entity/ticket.dart';

abstract class PaymentRemoteDataSource {
  Future<PaymentModel> paymentTicket({
    required int amount,
    required String currency,
    required PayMethodEnum paymentMethod,
    required Ticket ticket,
  });

  Future<PaymentModel?> getPayment({required String paymentId});

  Future<List<PaymentModel?>> getUserPaymentTicket();

  Future<PaymentModel> refundTicket({
    required int amount,
    required String currency,
    required Payment payment,
    required PayMethodEnum paymentMethod,
    required Ticket ticket,
  });
}

class PaymentRemoteDataSourceImpl implements PaymentRemoteDataSource {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  final FirebaseAuth _auth = FirebaseAuth.instance;

  final Dio _dio = Dio(
    BaseOptions(
      baseUrl: AppUrl.urlPay + AppUrl.versionPayApi,
      connectTimeout: const Duration(seconds: 10),
      receiveTimeout: const Duration(seconds: 10),
      headers: {
        'Authorization': 'Bearer ${AppUrl.secretKey}',
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    ),
  );

  @override
  Future<PaymentModel> paymentTicket({
    required int amount,
    required String currency,
    required PayMethodEnum paymentMethod,
    required Ticket ticket,
  }) async {
    try {
      final response = await _dio.post(
        AppUrl.urlPayINTENT,
        data: {"amount": amount, "currency": currency},
        options: Options(
          contentType: Headers.formUrlEncodedContentType,
          headers: {
            'Authorization': 'Bearer ${AppUrl.secretKey}',
            'Content-Type': 'application/x-www-form-urlencoded',
          },
        ),
      );

      if (response.data == null || response.data['client_secret'] == null) {
        printE("Payment failed: Invalid response from server.");
        throw Exception("Invalid response from payment server.");
      }

      final String clientSecret = response.data['client_secret'];
      final String paymentIntentId = response.data['id']; // Lấy ID từ Stripe

      await Stripe.instance.initPaymentSheet(
        paymentSheetParameters: SetupPaymentSheetParameters(
          paymentIntentClientSecret: clientSecret,
          merchantDisplayName: 'Vinemas',
        ),
      );
      await Stripe.instance.presentPaymentSheet();

      // Lấy thông tin user
      final String userAuthId = _auth.currentUser?.uid ?? '';
      if (userAuthId.isEmpty) {
        printE("Error: User not authenticated.");
        throw Exception("User authentication failed.");
      }

      // Tạo bill thanh toán
      PaymentModel paymentModel = PaymentModel(
        paymentId: paymentIntentId, // Dùng luôn ID từ Stripe
        userAuthId: userAuthId,
        ticketId: ticket.ticketId,
        paymentMethod: paymentMethod,
        paymentStatus: PayStatusEnum.completed,
        content: 'Payment successful',
        updateAt: Timestamp.now().toDate(),
        createdAt: Timestamp.now().toDate(),
      );

      // Lưu vào Firestore với ID là paymentIntentId
      DocumentReference docRef = _firestore
          .collection('payment')
          .doc(paymentIntentId);
      await docRef.set(paymentModel.toMap());

      printS(
        "Payment successful and saved to Firestore: ${paymentModel.paymentId}",
      );

      return paymentModel;
    } catch (e) {
      printE("Unexpected Error: $e");
      throw Exception("Unexpected payment error: $e");
    }
  }



  @override
  Future<PaymentModel?> getPayment({required String paymentId}) async {
    try {
      DocumentSnapshot snapshot =
          await _firestore.collection('payment').doc(paymentId).get();

      PaymentModel payment = PaymentModel.fromJson(
        snapshot.data() as Map<String, dynamic>,
      );

      printS("Retrieved $payment payment records from Firestore.");
      return payment;
    } catch (e) {
      printE("Error fetching payment tickets: $e");
      throw Exception("Failed to fetch payment tickets.");
    }
  }

  @override
  Future<List<PaymentModel>> getUserPaymentTicket() async {
    try {
      final String userAuthId = _auth.currentUser?.uid ?? '';
      if (userAuthId.isEmpty) {
        printE("Error: User not authenticated.");
        return [];
      }

      QuerySnapshot snapshot =
          await _firestore
              .collection('payment')
              .where('userAuthId', isEqualTo: userAuthId)
              .get();

      if (snapshot.docs.isEmpty) {
        printE("No payment records found for user $userAuthId.");
        return [];
      }

      List<PaymentModel> payments =
          snapshot.docs.map((doc) {
            Map<String, dynamic> data = doc.data() as Map<String, dynamic>;
            data['paymentId'] = doc.id; // Gán document ID vào paymentId
            return PaymentModel.fromJson(data);
          }).toList();

      printS("Retrieved ${payments.length} payments for user: $userAuthId");
      return payments;
    } catch (e) {
      printE("Error fetching user's payment tickets: $e");
      return [];
    }
  }

  @override
  Future<PaymentModel> refundTicket({
    required int amount,
    required String currency,
    required Payment payment, // Payment từ Firestore chứa Stripe Payment ID
    required PayMethodEnum paymentMethod,
    required Ticket ticket,
  }) async {
    try {
      // Kiểm tra Payment ID hợp lệ
      if (payment.paymentId.isEmpty) {
        throw Exception("Invalid payment ID. Refund cannot be processed.");
      }

      // Gửi yêu cầu hoàn tiền đến Stripe
      final response = await _dio.post(
        AppUrl.urlPayRefund,
        data: {'payment_intent': payment.paymentId, 'amount': amount},
        options: Options(
          contentType: Headers.formUrlEncodedContentType,
          headers: {
            'Authorization': 'Bearer ${AppUrl.secretKey}',
            'Content-Type': 'application/x-www-form-urlencoded',
          },
        ),
      );

      if (response.data == null || response.data['id'] == null) {
        printE("Refund failed: Invalid response from Stripe.");
        throw Exception("Invalid response from Stripe refund API.");
      }

      final String refundId = response.data['id']; // Lấy Refund ID từ Stripe

      // Lấy thông tin user
      final String userAuthId = _auth.currentUser?.uid ?? '';
      if (userAuthId.isEmpty) {
        printE("Error: User not authenticated.");
        throw Exception("User authentication failed.");
      }

      // Tạo bill hoàn tiền
      PaymentModel refundModel = PaymentModel(
        paymentId: refundId, // Sử dụng Refund ID từ Stripe
        userAuthId: userAuthId,
        ticketId: ticket.ticketId,
        paymentMethod: paymentMethod,
        paymentStatus: PayStatusEnum.refunded,
        content: 'Refund successful',
        updateAt: Timestamp.now().toDate(),
        createdAt: Timestamp.now().toDate(),
      );

      // Lưu vào Firestore
      DocumentReference docRef = _firestore
          .collection('payment')
          .doc(refundId);
      await docRef.set(refundModel.toMap());

      // Cập nhật ID Firestore vào model
      PaymentModel savedPayment = refundModel.copyWith(paymentId: docRef.id);

      printS(
        "Refund successful and saved to Firestore: ${savedPayment.paymentId}",
      );

      return savedPayment;
    } catch (e) {
      printE("Unexpected Refund Error: $e");
      throw Exception("Unexpected refund error: $e");
    }
  }

}
