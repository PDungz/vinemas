package com.example.vinemas_server.domain.repository.cinema_band_repository;

import com.example.vinemas_server.domain.model.cinema_band.CinemaBand;

import java.util.List;
import java.util.Optional;

public interface CinemaBandRepository {
    List<CinemaBand> findAll();
    Optional<CinemaBand> findById(String id);
    CinemaBand save(CinemaBand cinemaBand);
    void deleteById(String id);
    boolean existsById(String id);
}
