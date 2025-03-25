import 'package:vinemas_app/features/about_sessions/domain/entity/about/movie_detail.dart';

abstract class MovieDetailRepository {
  Future<MovieDetail?> getMovieDetail({
    required int movieId,
    String language = 'en',
  });
}
