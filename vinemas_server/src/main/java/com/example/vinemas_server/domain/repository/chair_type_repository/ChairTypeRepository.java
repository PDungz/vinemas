package com.example.vinemas_server.domain.repository.chair_type_repository;

import com.example.vinemas_server.domain.model.chair_type.ChairType;

import java.util.List;
import java.util.Optional;

public interface ChairTypeRepository {
    ChairType save(ChairType chairType);
    Optional<ChairType> findById(Long chairTypeId);
    List<ChairType> findAll();
    void deleteById(long id);
    boolean existsById(long id);
}
