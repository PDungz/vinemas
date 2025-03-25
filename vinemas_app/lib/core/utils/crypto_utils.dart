import 'dart:convert';
import 'dart:math';
import 'package:crypto/crypto.dart';

/// Tạo một chuỗi nonce ngẫu nhiên để sử dụng trong OAuth 2.0
String generateNonce([int length = 32]) {
  const charset =
      '0123456789ABCDEFGHIJKLMNOPQRSTUVXYZabcdefghijklmnopqrstuvwxyz-._';
  final random = Random.secure();
  return List.generate(
    length,
    (_) => charset[random.nextInt(charset.length)],
  ).join();
}

/// Mã hóa một chuỗi bằng SHA-256
String sha256ofString(String input) {
  final bytes = utf8.encode(input);
  final digest = sha256.convert(bytes);
  return digest.toString();
}
