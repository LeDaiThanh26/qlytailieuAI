package com.example.qlytailieuAI.qlytailieuAI.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateRoleRequest {
    @NotNull(message = "Mã vai trò không được để trống")
    @Positive(message = "Mã vai trò phải là số dương")
    private Integer roleId;
}
