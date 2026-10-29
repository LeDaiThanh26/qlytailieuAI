package com.example.qlytailieuAI.qlytailieuAI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    @Builder.Default
    private int code = 1000;

    @Builder.Default
    private String message = "Success";

    private T data;
}
