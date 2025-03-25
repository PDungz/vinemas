import 'package:vinemas_app/core/global/api/configuration/domain/entity/image_config.dart';

class Configuration {
  List<String> changeKeys;
  ImageConfig imageConfig;
  Configuration({required this.changeKeys, required this.imageConfig});

  Configuration copyWith({List<String>? changeKeys, ImageConfig? imageConfig}) {
    return Configuration(
      changeKeys: changeKeys ?? this.changeKeys,
      imageConfig: imageConfig ?? this.imageConfig,
    );
  }
}
