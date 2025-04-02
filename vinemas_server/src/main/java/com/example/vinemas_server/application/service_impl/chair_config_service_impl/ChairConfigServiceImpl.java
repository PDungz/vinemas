package com.example.vinemas_server.application.service_impl.chair_config_service_impl;

import com.example.vinemas_server.application.service.chair_config_service.ChairConfigService;
import com.example.vinemas_server.domain.model.chair_config.ChairConfig;
import com.example.vinemas_server.domain.repository.chair_config_repository.ChairConfigRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChairConfigServiceImpl implements ChairConfigService {
    private  final ChairConfigRepository chairConfigRepository;

    public ChairConfigServiceImpl(ChairConfigRepository chairConfigRepository) {
        this.chairConfigRepository = chairConfigRepository;
    }

    @Override
    public ChairConfig createChairConfig(ChairConfig chairConfig) {
        return chairConfigRepository.save(chairConfig);
    }

    @Override
    public Optional<ChairConfig> getChairConfigById(long id) {
        if (chairConfigRepository.existsById(id)) {
            throw new IllegalArgumentException("ChairConfig not found");
        }
        return chairConfigRepository.findById(id);
    }

    @Override
    public List<ChairConfig> getAllChairConfig() {
        return chairConfigRepository.findAll();
    }

    @Override
    public ChairConfig updateChairConfig(long id, ChairConfig chairConfig) {
        return chairConfigRepository.findById(id).map(existingChairConfig-> {
            existingChairConfig.setLayout(chairConfig.getLayout());
            existingChairConfig.setRowCount(chairConfig.getRowCount());
            existingChairConfig.setSeatsPerRow(chairConfig.getSeatsPerRow());
            return chairConfigRepository.save(existingChairConfig);
        }).orElseThrow(() -> new RuntimeException("ChairConfig not found"));
    }

    @Override
    public void deleteChairConfig(long id) {
        if (!chairConfigRepository.existsById(id)) {
            throw new IllegalArgumentException("ChairConfig not found");
        }
        chairConfigRepository.deleteById(id);
    }
}
