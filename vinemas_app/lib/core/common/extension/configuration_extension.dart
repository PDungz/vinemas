import 'package:vinemas_app/core/common/enum/configuration.dart';
import 'package:vinemas_app/core/global/api/configuration/domain/entity/configuration.dart';

extension PosterUrlExtension on Configuration {
  String? getPosterUrl(
    String? posterPath, {
    PosterSize size = PosterSize.original,
  }) {
    if (posterPath == null) return null;
    return "${imageConfig.baseUrl}${imageConfig.getPosterSizes(size.name)}$posterPath";
  }
}
