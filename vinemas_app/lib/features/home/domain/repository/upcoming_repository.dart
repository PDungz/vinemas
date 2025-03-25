import 'package:vinemas_app/features/home/domain/entity/movie.dart';

abstract class UpcomingRepository {
  Future<List<Movie>?> getUpcomming({String language = 'en', int page = 1});
}
