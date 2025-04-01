package com.example.vinemas_server.domain.repository.chair_config_repository;

import com.example.vinemas_server.domain.model.chair_config.ChairConfig;

import java.util.List;
import java.util.Optional;

public interface ChairConfigRepository {
    ChairConfig save(ChairConfig chairConfig);
    Optional<ChairConfig> findById(long id);
    List<ChairConfig> findAll();
    void deleteById(long id);
    boolean existsById(long id);
}
