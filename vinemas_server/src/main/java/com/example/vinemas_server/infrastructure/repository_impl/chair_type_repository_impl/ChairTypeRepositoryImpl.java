package com.example.vinemas_server.infrastructure.repository_impl.chair_type_repository_impl;

import com.example.vinemas_server.domain.model.chair_type.ChairType;
import com.example.vinemas_server.domain.repository.chair_type_repository.ChairTypeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairTypeRepositoryImpl extends JpaRepository<ChairType, Long>, ChairTypeRepository {
}
