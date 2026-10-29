package com.example.qlytailieuAI.qlytailieuAI.controller;

import java.util.List;

import com.example.qlytailieuAI.qlytailieuAI.dto.request.ApiResponse;
import com.example.qlytailieuAI.qlytailieuAI.dto.request.UpdateRoleRequest;
import com.example.qlytailieuAI.qlytailieuAI.dto.request.UpdateStatusRequest;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.UserResponse;
import com.example.qlytailieuAI.qlytailieuAI.service.AdminUserService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminUserController {
    AdminUserService adminUserService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .data(adminUserService.getUsers())
                .build();
    }

    @PatchMapping("/{userId}/status")
    public ApiResponse<UserResponse> updateStatus(
            @PathVariable Integer userId,
            @RequestBody @Valid UpdateStatusRequest request) {
        return ApiResponse.<UserResponse>builder()
                .data(adminUserService.updateStatus(userId, request))
                .build();
    }

    @PatchMapping("/{userId}/role")
    public ApiResponse<UserResponse> updateRole(
            @PathVariable Integer userId,
            @RequestBody @Valid UpdateRoleRequest request) {
        return ApiResponse.<UserResponse>builder()
                .data(adminUserService.updateRole(userId, request))
                .build();
    }
}
