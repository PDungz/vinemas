package com.example.vinemas_server.adapter.controller.user_controller;

import com.example.vinemas_server.application.service.user_service.UserService;
import com.example.vinemas_server.application.use_case.user_use_case.CreateUserUseCase;
import com.example.vinemas_server.application.use_case.user_use_case.GetAllUsersUseCase;
import com.example.vinemas_server.domain.model.user.User;
import com.example.vinemas_server.adapter.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final CreateUserUseCase createUserUseCase;

    public UserController(UserService userService, GetAllUsersUseCase getAllUsersUseCase, CreateUserUseCase createUserUseCase) {
        this.userService = userService;
        this.getAllUsersUseCase = getAllUsersUseCase;
        this.createUserUseCase = createUserUseCase;
    }

    // Tạo user với kiểm tra đầu vào
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(createUserUseCase.execute(user));
    }

    // Lấy user theo ID, nếu không có thì ném NotFoundException
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    // Lấy tất cả users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(getAllUsersUseCase.execute());
    }

    // Cập nhật user với kiểm tra đầu vào
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // Xóa user theo ID, nếu không có thì ném NotFoundException
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        if (userService.getUserById(id).isEmpty()) {
            throw new NotFoundException("User not found with ID: " + id);
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
