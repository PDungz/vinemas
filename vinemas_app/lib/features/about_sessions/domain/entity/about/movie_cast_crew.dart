import 'package:vinemas_app/features/about_sessions/domain/entity/about/cast.dart';
import 'package:vinemas_app/features/about_sessions/domain/entity/about/crew.dart';

class MovieCastCrew {
  final int id;
  final List<Cast> cast;
  final List<Crew> crew;

  MovieCastCrew({required this.id, required this.cast, required this.crew});
}
