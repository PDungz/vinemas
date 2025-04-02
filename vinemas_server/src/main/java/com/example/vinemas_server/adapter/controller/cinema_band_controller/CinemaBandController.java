package com.example.vinemas_server.adapter.controller.cinema_band_controller;

import com.example.vinemas_server.application.service.cinema_band_service.CinemaBandService;
import com.example.vinemas_server.domain.model.cinema_band.CinemaBand;
import com.example.vinemas_server.adapter.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemaBands") // Định nghĩa API endpoint
public class CinemaBandController {

    private final CinemaBandService cinemaBandService;

    public CinemaBandController(CinemaBandService cinemaBandService) {
        this.cinemaBandService = cinemaBandService;
    }

    // 🔹 Tạo mới Cinema Band
    @PostMapping
    public ResponseEntity<CinemaBand> createCinemaBand(@Valid @RequestBody CinemaBand cinemaBand) {
        return ResponseEntity.ok(cinemaBandService.createCinemaBand(cinemaBand));
    }

    // 🔹 Lấy danh sách tất cả Cinema Band
    @GetMapping
    public ResponseEntity<List<CinemaBand>> getAllCinemaBands() {
        return ResponseEntity.ok(cinemaBandService.getAllCinemaBands());
    }

    // 🔹 Lấy Cinema Band theo ID
    @GetMapping("/{id}")
    public ResponseEntity<CinemaBand> getCinemaBandById(@PathVariable String id) {
        return cinemaBandService.getCinemaBandById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Cinema Band not found with ID: " + id));
    }

    // 🔹 Cập nhật Cinema Band theo ID
    @PutMapping("/{id}")
    public ResponseEntity<CinemaBand> updateCinemaBand(@PathVariable String id, @Valid @RequestBody CinemaBand updatedCinemaBand) {
        return ResponseEntity.ok(cinemaBandService.updateCinemaBand(id, updatedCinemaBand));
    }

    // 🔹 Xóa Cinema Band theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCinemaBand(@PathVariable String id) {
        if (cinemaBandService.getCinemaBandById(id).isEmpty()) {
            throw new NotFoundException("Cinema Band not found with ID: " + id);
        }
        cinemaBandService.deleteCinemaBand(id);
        return ResponseEntity.noContent().build();
    }
}
