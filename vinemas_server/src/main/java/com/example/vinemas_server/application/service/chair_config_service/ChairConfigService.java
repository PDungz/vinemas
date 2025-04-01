package com.example.vinemas_server.application.service.chair_config_service;

import com.example.vinemas_server.domain.model.chair_config.ChairConfig;

import java.util.List;
import java.util.Optional;

public interface ChairConfigService {
    ChairConfig createChairConfig(ChairConfig chairConfig);
    Optional<ChairConfig> getChairConfigById(long id);
    List<ChairConfig> getAllChairConfig();
    ChairConfig updateChairConfig(long id, ChairConfig chairConfig);
    void deleteChairConfig(long id);
}
