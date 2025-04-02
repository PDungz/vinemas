package com.example.vinemas_server.application.service_impl.chair_type_service_impl;

import com.example.vinemas_server.application.service.chair_type_service.ChairTypeService;
import com.example.vinemas_server.domain.model.chair_type.ChairType;
import com.example.vinemas_server.domain.repository.chair_type_repository.ChairTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChairTypeServiceImpl implements ChairTypeService {

    private final ChairTypeRepository chairTypeRepository;

    public ChairTypeServiceImpl(ChairTypeRepository chairTypeRepository) {
        this.chairTypeRepository = chairTypeRepository;
    }

    @Override
    public ChairType createChairType(ChairType chairType) {
        if (chairTypeRepository.existsById(chairType.getChairTypeId())) {
            throw new IllegalArgumentException("ChairType ID already exists");
        }
        return chairTypeRepository.save(chairType);
    }

    @Override
    public Optional<ChairType> getChairTypeById(Long id) {
        return chairTypeRepository.findById(id);
    }

    @Override
    public List<ChairType> getAllChairTypes() {
        return chairTypeRepository.findAll();
    }

    @Override
    public ChairType updateChairType(Long id, ChairType chairType) {
        return chairTypeRepository.findById(id).map(existingChairType-> {
            existingChairType.setChairConfig(chairType.getChairConfig());
            existingChairType.setSeatRow(chairType.getSeatRow());
            existingChairType.setSeatTypeName(chairType.getSeatTypeName());
            return chairTypeRepository.save(existingChairType);
        }).orElseThrow(() -> new RuntimeException("ChairType not found"));
    }

    @Override
    public void deleteChairType(Long id) {
        if (!chairTypeRepository.existsById(id)) {
            throw new IllegalArgumentException("ChairType ID already exists");
        }
        chairTypeRepository.deleteById(id);
    }
}
