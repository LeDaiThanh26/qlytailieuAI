package com.example.qlytailieuAI.qlytailieuAI.service;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import com.example.qlytailieuAI.qlytailieuAI.configuration.JwtService;
import com.example.qlytailieuAI.qlytailieuAI.constant.PredefinedRole;
import com.example.qlytailieuAI.qlytailieuAI.dto.request.*;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.AuthenticationResponse;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.IntrospectResponse;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.UserResponse;
import com.example.qlytailieuAI.qlytailieuAI.entity.NguoiDung;
import com.example.qlytailieuAI.qlytailieuAI.entity.TokenDaThuHoi;
import com.example.qlytailieuAI.qlytailieuAI.entity.VaiTro;
import com.example.qlytailieuAI.qlytailieuAI.exception.AppException;
import com.example.qlytailieuAI.qlytailieuAI.exception.ErrorCode;
import com.example.qlytailieuAI.qlytailieuAI.mapper.UserMapper;
import com.example.qlytailieuAI.qlytailieuAI.repository.NguoiDungRepository;
import com.example.qlytailieuAI.qlytailieuAI.repository.TokenDaThuHoiRepository;
import com.example.qlytailieuAI.qlytailieuAI.repository.VaiTroRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    NguoiDungRepository nguoiDungRepository;
    VaiTroRepository vaiTroRepository;
    TokenDaThuHoiRepository tokenDaThuHoiRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;

    @Transactional
    public UserResponse register(RegisterRequest request) {
        String email = request.getEmail().trim().toLowerCase(Locale.ROOT);
        if (nguoiDungRepository.existsByEmailIgnoreCase(email)) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        VaiTro userRole = vaiTroRepository.findByTenVaiTroIgnoreCase(PredefinedRole.USER_ROLE)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));

        NguoiDung user = new NguoiDung();
        user.setHoTen(request.getFullName().trim());
        user.setEmail(email);
        user.setMatKhau(passwordEncoder.encode(request.getPassword()));
        user.setTrangThai("ACTIVE");
        user.setVaiTro(userRole);

        try {
            return userMapper.toUserResponse(nguoiDungRepository.save(user));
        } catch (DataIntegrityViolationException exception) {
            log.warn("Registration failed due to database constraint", exception);
            throw new AppException(ErrorCode.USER_EXISTED);
        }
    }

    @Transactional(readOnly = true)
    public AuthenticationResponse login(AuthenticationRequest request) {
        NguoiDung user = nguoiDungRepository.findByEmailIgnoreCase(request.getEmail().trim())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_CREDENTIALS));

        if (!passwordEncoder.matches(request.getPassword(), user.getMatKhau())) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }
        if (!isActive(user)) {
            throw new AppException(ErrorCode.ACCOUNT_LOCKED);
        }
        return createAuthenticationResponse(user);
    }

    @Transactional
    public AuthenticationResponse refresh(RefreshRequest request) {
        JWTClaimsSet claims = readValidToken(request.getRefreshToken(), "refresh");
        if (tokenDaThuHoiRepository.existsById(claims.getJWTID())) {
            throw new AppException(ErrorCode.TOKEN_INVALID);
        }

        NguoiDung user = nguoiDungRepository.findByEmailIgnoreCase(claims.getSubject())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        if (!isActive(user)) {
            throw new AppException(ErrorCode.ACCOUNT_LOCKED);
        }

        revokeToken(claims);
        return createAuthenticationResponse(user);
    }

    @Transactional
    public void logout(String authorizationHeader) {
        String token = authorizationHeader.replaceFirst("(?i)^Bearer\\s+", "").trim();
        revokeTokenSafely(token);
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = jwtService.parseAndVerify(request.getToken());
        JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
        Date expirationTime = claims.getExpirationTime();
        if (expirationTime == null || !expirationTime.after(new Date())) {
            return IntrospectResponse.builder().valid(false).build();
        }
        if (!"access".equals(claims.getStringClaim("token_type"))
                || claims.getJWTID() == null
                || tokenDaThuHoiRepository.existsById(claims.getJWTID())) {
            return IntrospectResponse.builder().valid(false).build();
        }

        NguoiDung user = nguoiDungRepository.findByEmailIgnoreCase(claims.getSubject()).orElse(null);
        if (user == null || !isActive(user)) {
            return IntrospectResponse.builder().valid(false).build();
        }
        return IntrospectResponse.builder()
                .valid(Objects.equals(claims.getStringClaim("scope"), toAuthority(user.getVaiTro().getTenVaiTro())))
                .build();
    }

    private AuthenticationResponse createAuthenticationResponse(NguoiDung user) {
        String authority = toAuthority(user.getVaiTro().getTenVaiTro());
        String accessToken = jwtService.generateToken(user.getEmail(), authority, "access",
                jwtService.getAccessTokenValidityInSeconds());
        String refreshToken = jwtService.generateToken(user.getEmail(), authority, "refresh",
                jwtService.getRefreshTokenValidityInSeconds());

        return AuthenticationResponse.builder()
                .authenticated(true)
                .tokenType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(jwtService.getAccessTokenValidityInSeconds())
                .build();
    }

    private JWTClaimsSet readValidToken(String token, String expectedType) {
        try {
            SignedJWT signedJWT = jwtService.parseAndVerify(token);
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            if (claims.getExpirationTime() == null || !claims.getExpirationTime().after(new Date())
                    || !expectedType.equals(claims.getStringClaim("token_type"))
                    || claims.getJWTID() == null) {
                throw new AppException(ErrorCode.TOKEN_INVALID);
            }
            return claims;
        } catch (ParseException | JOSEException exception) {
            throw new AppException(ErrorCode.TOKEN_INVALID);
        }
    }

    private void revokeTokenSafely(String token) {
        if (token == null || token.isBlank()) {
            return;
        }
        try {
            JWTClaimsSet claims = jwtService.parseAndVerify(token).getJWTClaimsSet();
            revokeToken(claims);
        } catch (ParseException | JOSEException | RuntimeException exception) {
            log.debug("Skip token revocation because token is invalid", exception);
        }
    }

    private void revokeToken(JWTClaimsSet claims) {
        try {
            String jti = claims.getJWTID();
            Date expirationTime = claims.getExpirationTime();
            if (jti != null && expirationTime != null && expirationTime.after(new Date())
                    && !tokenDaThuHoiRepository.existsById(jti)) {
                tokenDaThuHoiRepository.save(TokenDaThuHoi.builder()
                        .jti(jti)
                        .ngayHetHan(Instant.ofEpochMilli(expirationTime.getTime()))
                        .build());
            }
        } catch (RuntimeException exception) {
            log.error("Cannot revoke token", exception);
            throw exception;
        }
    }

    private boolean isActive(NguoiDung user) {
        return "ACTIVE".equalsIgnoreCase(user.getTrangThai());
    }

    private String toAuthority(String roleName) {
        if (roleName == null || roleName.isBlank()) {
            return "ROLE_USER";
        }
        String normalizedRole = roleName.trim().toUpperCase(Locale.ROOT);
        return normalizedRole.startsWith("ROLE_") ? normalizedRole : "ROLE_" + normalizedRole;
    }
}
