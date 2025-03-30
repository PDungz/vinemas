package com.example.vinemas_server.application.service.cinema_band_service;

import com.example.vinemas_server.domain.model.cinema_band.CinemaBand;


import java.util.List;
import java.util.Optional;

public interface CinemaBandService {
    CinemaBand createCinemaBand(CinemaBand cinemaBand);
    Optional<CinemaBand> getCinemaBandById(String id);
    List<CinemaBand> getAllCinemaBands();  // <-- findAll
    CinemaBand updateCinemaBand(String id, CinemaBand cinemaBand);
    void deleteCinemaBand(String id);
}
