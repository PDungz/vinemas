// ignore_for_file: public_member_api_docs, sort_constructors_first
import 'package:dio/dio.dart';
import 'package:vinemas_app/core/config/app_url.dart';
import 'package:vinemas_app/core/global/local_data/shared_preferences/domain/use_case/shared_preference_use_case.dart';
import 'package:vinemas_app/core/service/injection_container.dart';
import 'package:vinemas_app/core/service/logger_service.dart';
import 'package:vinemas_app/features/about_sessions/data/model/about/movie_cast_crew_model.dart';

abstract class CastRemoteDataSource {
  Future<MovieCastCrewModel?> getCast({
    required int movieId,
    String language = 'en',
  });
}

class CastRemoteDataSourceImpl implements CastRemoteDataSource {
  final Dio dio;

  CastRemoteDataSourceImpl({required this.dio});

  @override
  Future<MovieCastCrewModel?> getCast({
    required int movieId,
    String language = 'en',
  }) async {
    try {
      final String localLanguage =
          await getIt<SharedPreferenceUseCase>().getData('language') ??
          language;

      final response = await dio.get(
        AppUrl.movieCast(movieId),
        queryParameters: {'language': localLanguage},
      );

      if (response.statusCode == 200 && response.data != null) {
        printS("Success in CastRemoteDataSourceImpl: ${response.data}");

        return MovieCastCrewModel.fromJson(response.data);
      }
    } catch (e) {
      printE("Error in CastRemoteDataSourceImpl: $e");
    }
    return null;
  }
}
