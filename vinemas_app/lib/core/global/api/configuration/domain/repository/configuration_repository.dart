import 'package:vinemas_app/core/global/api/configuration/domain/entity/configuration.dart';

abstract class ConfigurationRepository {
  Future<Configuration?> getConfiguration();
}
