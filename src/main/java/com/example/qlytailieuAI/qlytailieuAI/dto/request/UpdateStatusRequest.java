package com.example.qlytailieuAI.qlytailieuAI.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    @NotBlank(message = "Trạng thái không được để trống")
    @Pattern(regexp = "ACTIVE|LOCKED", message = "Trạng thái chỉ nhận ACTIVE hoặc LOCKED")
    private String status;
}
