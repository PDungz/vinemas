import 'package:get/get.dart';
import 'package:vinemas_app/core/config/app_router.dart';
import 'package:vinemas_app/features/about_sessions/presentation/about_sessions_page.dart';
import 'package:vinemas_app/features/chat_bot/presentation/chat_bot.dart';
import 'package:vinemas_app/features/home/presentation/home_page.dart';
import 'package:vinemas_app/features/login/presentation/login_page.dart';
import 'package:vinemas_app/features/login/presentation/page/enter_verification_code_page.dart';
import 'package:vinemas_app/features/login/presentation/page/forgot_password_page.dart';
import 'package:vinemas_app/features/login/presentation/page/sign_up_page.dart';
import 'package:vinemas_app/features/pay/presentation/pay_page.dart';
import 'package:vinemas_app/features/profile/presentation/profile_page.dart';
import 'package:vinemas_app/features/seat_reservation/presentation/seat_reservation_page.dart';
import 'package:vinemas_app/features/splash/presentation/splash_page.dart';
import 'package:vinemas_app/features/ticket/presentation/page/ticket_detail_page.dart';
import 'package:vinemas_app/features/ticket/presentation/ticket_page.dart';

class AppGenerateRouter {
  static final routes = [
    GetPage(name: ConfigRoute.splashPage, page: () => SplashPage()),
    GetPage(name: ConfigRoute.loginPage, page: () => LoginPage()),
    GetPage(name: ConfigRoute.signUpPage, page: () => SignUpPage()),
    GetPage(
      name: ConfigRoute.forgotPasswordPage,
      page: () => ForgotPasswordPage(),
    ),
    GetPage(
      name: ConfigRoute.verifyYourAccountPage,
      page: () => EnterVerificationCodePage(),
    ),
    GetPage(name: ConfigRoute.homePage, page: () => HomePage()),
    GetPage(
      name: ConfigRoute.aboutSessionsPage,
      page: () => AboutSessionsPage(),
    ),
    GetPage(
      name: ConfigRoute.seatReservationPage,
      page: () => SeatReservationPage(),
    ),
    GetPage(name: ConfigRoute.payPage, page: () => PayPage()),
    GetPage(name: ConfigRoute.ticketPage, page: () => TicketPage()),
    GetPage(name: ConfigRoute.ticketDetailPage, page: () => TicketDetailPage()),
    GetPage(name: ConfigRoute.profilePage, page: () => ProfilePage()),
    GetPage(name: ConfigRoute.chatBotPage, page: () => ChatBot()),
  ];
}
