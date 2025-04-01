package com.example.vinemas_server.adapter.controller.chair_type_controller;

import com.example.vinemas_server.application.service.chair_type_service.ChairTypeService;
import com.example.vinemas_server.domain.model.chair_type.ChairType;
import com.example.vinemas_server.adapter.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chairTypes")
public class ChairTypeController {

    private final ChairTypeService chairTypeService;

    public ChairTypeController(ChairTypeService chairTypeService) {
        this.chairTypeService = chairTypeService;
    }

    // Tạo mới một ChairType
    @PostMapping
    public ResponseEntity<ChairType> createChairType(@Valid @RequestBody ChairType chairType) {
        return ResponseEntity.ok(chairTypeService.createChairType(chairType));
    }

    // Lấy tất cả ChairType
    @GetMapping
    public ResponseEntity<List<ChairType>> getAllChairTypes() {
        return ResponseEntity.ok(chairTypeService.getAllChairTypes());
    }

    // Lấy một ChairType theo ID
    @GetMapping("/{chairTypeId}")
    public ResponseEntity<ChairType> getChairTypeById(@PathVariable Long chairTypeId) {
        return chairTypeService.getChairTypeById(chairTypeId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("ChairType not found with ID: " + chairTypeId));
    }

    // Cập nhật một ChairType
    @PutMapping("/{chairTypeId}")
    public ResponseEntity<ChairType> updateChairType(@PathVariable Long chairTypeId, @Valid @RequestBody ChairType chairType) {
        return ResponseEntity.ok(chairTypeService.updateChairType(chairTypeId, chairType));
    }

    // Xóa một ChairType
    @DeleteMapping("/{chairTypeId}")
    public ResponseEntity<Void> deleteChairType(@PathVariable Long chairTypeId) {
        chairTypeService.deleteChairType(chairTypeId);
        return ResponseEntity.noContent().build();
    }
}
