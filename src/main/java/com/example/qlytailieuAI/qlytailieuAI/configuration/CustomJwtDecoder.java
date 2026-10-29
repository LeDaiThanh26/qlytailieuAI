package com.example.qlytailieuAI.qlytailieuAI.configuration;

import java.nio.charset.StandardCharsets;

import javax.crypto.spec.SecretKeySpec;

import com.example.qlytailieuAI.qlytailieuAI.repository.TokenDaThuHoiRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtDecoder implements JwtDecoder {

    private final NimbusJwtDecoder nimbusJwtDecoder;
    private final TokenDaThuHoiRepository invalidatedTokenRepository;

    public CustomJwtDecoder(
            @Value("${jwt.signerkey}") String signerKey,
            TokenDaThuHoiRepository invalidatedTokenRepository) {

        SecretKeySpec secretKeySpec = new SecretKeySpec(
                signerKey.getBytes(StandardCharsets.UTF_8),
                "HmacSHA512"
        );

        this.nimbusJwtDecoder = NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();

        this.invalidatedTokenRepository = invalidatedTokenRepository;
    }

    @Override
    public Jwt decode(String token) throws JwtException {

        // Xác minh chữ ký và thời hạn JWT
        Jwt jwt = nimbusJwtDecoder.decode(token);

        // Token phải có JWT ID (jti)
        String jwtId = jwt.getId();

        if (jwtId == null || jwtId.isBlank()) {
            throw new JwtException("Token không có JWT ID");
        }

        // Kiểm tra token đã bị thu hồi hay chưa
        if (invalidatedTokenRepository.existsById(jwtId)) {
            throw new JwtException("Token đã bị thu hồi");
        }

        return jwt;
    }
}