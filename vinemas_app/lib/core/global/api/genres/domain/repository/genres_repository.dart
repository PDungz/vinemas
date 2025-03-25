import 'package:vinemas_app/core/global/api/genres/domain/entity/genres.dart';

abstract class GenresRepository {
  Future<List<Genres>?> getGenres();
}
