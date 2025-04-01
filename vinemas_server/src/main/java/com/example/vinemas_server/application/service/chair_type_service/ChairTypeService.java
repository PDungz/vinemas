package com.example.vinemas_server.application.service.chair_type_service;

import com.example.vinemas_server.domain.model.chair_type.ChairType;

import java.util.List;
import java.util.Optional;

public interface ChairTypeService {
    ChairType createChairType(ChairType chairType);
    Optional<ChairType> getChairTypeById(Long chairTypeId);
    List<ChairType> getAllChairTypes();
    ChairType updateChairType(Long chairTypeId, ChairType chairType);
    void deleteChairType(Long chairTypeId);
}
