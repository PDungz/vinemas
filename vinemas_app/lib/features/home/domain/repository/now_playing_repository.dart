import 'package:vinemas_app/features/home/domain/entity/movie.dart';

abstract class NowPlayingRepository {
  Future<List<Movie>?> getNowPlaying({String language = 'en', int page = 1});
}
