package com.example.qlytailieuAI.qlytailieuAI.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private boolean authenticated;
    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
}
