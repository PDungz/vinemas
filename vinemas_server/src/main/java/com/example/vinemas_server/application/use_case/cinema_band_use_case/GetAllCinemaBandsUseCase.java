package com.example.vinemas_server.application.use_case.cinema_band_use_case;



import com.example.vinemas_server.application.service.cinema_band_service.CinemaBandService;
import com.example.vinemas_server.domain.model.cinema_band.CinemaBand;

import java.util.List;

public class GetAllCinemaBandsUseCase {
    private final CinemaBandService cinemaBandService;

    public GetAllCinemaBandsUseCase(CinemaBandService cinemaBandService) {
        this.cinemaBandService = cinemaBandService;
    }

    public List<CinemaBand> execute() {
        return cinemaBandService.getAllCinemaBands();
    }
}
