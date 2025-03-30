package com.example.vinemas_server.application.service_impl.cinema_band_server_imql;

import com.example.vinemas_server.application.service.cinema_band_service.CinemaBandService;
import com.example.vinemas_server.domain.model.cinema_band.CinemaBand;
import com.example.vinemas_server.domain.repository.cinema_band_repository.CinemaBandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CinemaBandServiceImpl implements CinemaBandService {
    private final CinemaBandRepository cinemaBandRepository;

    public CinemaBandServiceImpl(CinemaBandRepository cinemaBandRepository) {
        this.cinemaBandRepository = cinemaBandRepository;
    }

    @Override
    public CinemaBand createCinemaBand(CinemaBand cinemaBand) {
        // Tự động tạo ID nếu chưa có
        if (cinemaBand.getCinemaBandId() == null || cinemaBand.getCinemaBandId().isEmpty()) {
            String newUuid;
            do {
                newUuid = UUID.randomUUID().toString();
            } while (cinemaBandRepository.existsById(newUuid)); // Đảm bảo không bị trùng
            cinemaBand.setCinemaBandId(newUuid);
        }

        // Lưu vào database
        return cinemaBandRepository.save(cinemaBand);
    }


    @Override
    public Optional<CinemaBand> getCinemaBandById(String id) {
        return cinemaBandRepository.findById(id);
    }

    @Override
    public List<CinemaBand> getAllCinemaBands() {
        return cinemaBandRepository.findAll();
    }

    @Override
    public CinemaBand updateCinemaBand(String id, CinemaBand cinemaBand) {
        return cinemaBandRepository.findById(id).map(existingCinemaBand -> {
            existingCinemaBand.setNameCinema(cinemaBand.getNameCinema());
            existingCinemaBand.setImageUrl(cinemaBand.getImageUrl());
            existingCinemaBand.setOpenDate(cinemaBand.getOpenDate());
            existingCinemaBand.setCloseDate(cinemaBand.getCloseDate());
            existingCinemaBand.setDescription(cinemaBand.getDescription());
            return  cinemaBandRepository.save(existingCinemaBand);
        }).orElseThrow(() -> new RuntimeException("Cinema not found"));
    }

    @Override
    public void deleteCinemaBand(String id) {
        if(!cinemaBandRepository.existsById(id)) {
            throw new IllegalArgumentException("Cinema Band ID not found");
        }
        cinemaBandRepository.deleteById(id);
    }
}
