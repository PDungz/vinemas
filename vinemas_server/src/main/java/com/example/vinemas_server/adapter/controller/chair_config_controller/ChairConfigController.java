package com.example.vinemas_server.adapter.controller.chair_config_controller;

import com.example.vinemas_server.adapter.exception.NotFoundException;
import com.example.vinemas_server.application.service.chair_config_service.ChairConfigService;
import com.example.vinemas_server.domain.model.chair_config.ChairConfig;
import com.example.vinemas_server.domain.model.user.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chairConfigs")
public class ChairConfigController {
    private  final ChairConfigService chairConfigService;

    public ChairConfigController(ChairConfigService chairConfigService) {
        this.chairConfigService = chairConfigService;
    }

    @PostMapping
    public ResponseEntity<ChairConfig> createUser(@Valid @RequestBody ChairConfig chairConfig) {
        return ResponseEntity.ok(chairConfigService.createChairConfig(chairConfig));
    }

    @GetMapping
    public ResponseEntity<List<ChairConfig>> getAllChairConfig() {
        return ResponseEntity.ok(chairConfigService.getAllChairConfig());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChairConfig> getChairConfigById(@PathVariable Long id) {
        return chairConfigService.getChairConfigById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("ChairConfig not found with ID: " + id));
    }

    // Cập nhật user với kiểm tra đầu vào
    @PutMapping("/{id}")
    public ResponseEntity<ChairConfig> updateUser(@PathVariable Long id, @Valid @RequestBody ChairConfig chairConfig) {
        return ResponseEntity.ok(chairConfigService.updateChairConfig(id, chairConfig));
    }

    // Xóa user theo ID, nếu không có thì ném NotFoundException
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        chairConfigService.deleteChairConfig(id);
        return ResponseEntity.noContent().build();
    }
}
