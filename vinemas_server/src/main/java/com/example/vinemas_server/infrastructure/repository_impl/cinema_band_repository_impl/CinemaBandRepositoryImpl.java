package com.example.vinemas_server.infrastructure.repository_impl.cinema_band_repository_impl;

import com.example.vinemas_server.domain.model.cinema_band.CinemaBand;
import com.example.vinemas_server.domain.repository.cinema_band_repository.CinemaBandRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaBandRepositoryImpl extends JpaRepository<CinemaBand, String>, CinemaBandRepository {
}
