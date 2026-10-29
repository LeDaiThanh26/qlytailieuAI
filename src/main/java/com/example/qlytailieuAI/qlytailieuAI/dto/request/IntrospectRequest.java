package com.example.qlytailieuAI.qlytailieuAI.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IntrospectRequest {
    @NotBlank(message = "Token không được để trống")
    private String token;
}
