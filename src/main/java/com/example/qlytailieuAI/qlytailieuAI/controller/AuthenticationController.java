package com.example.qlytailieuAI.qlytailieuAI.controller;

import com.example.qlytailieuAI.qlytailieuAI.dto.request.*;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.AuthenticationResponse;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.UserResponse;
import com.example.qlytailieuAI.qlytailieuAI.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ApiResponse.<UserResponse>builder()
                .data(authenticationService.register(request))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .data(authenticationService.login(request))
                .build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refresh(@RequestBody @Valid RefreshRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .data(authenticationService.refresh(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(
            @RequestHeader("Authorization") String authorizationHeader) {

        authenticationService.logout(authorizationHeader);

        return ApiResponse.<Void>builder()
                .message("Đăng xuất thành công")
                .build();
    }
}
