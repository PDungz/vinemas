import 'package:dio/dio.dart';
import 'package:vinemas_app/core/config/app_url.dart';

class TokenInterceptor extends Interceptor {
  @override
  void onRequest(RequestOptions options, RequestInterceptorHandler handler) {
    // Add the token to the request headers
    options.headers['Authorization'] = 'Bearer ${AppUrl.apiKey}';
    return handler.next(options);
  }
}
