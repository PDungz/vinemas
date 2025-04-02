package com.example.vinemas_server.infrastructure.repository_impl.chair_config_repository_impl;

import com.example.vinemas_server.domain.model.chair_config.ChairConfig;
import com.example.vinemas_server.domain.repository.chair_config_repository.ChairConfigRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairConfigRepositoryImpl extends JpaRepository<ChairConfig, Long>, ChairConfigRepository {
}
