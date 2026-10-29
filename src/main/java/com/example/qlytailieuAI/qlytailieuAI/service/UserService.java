package com.example.qlytailieuAI.qlytailieuAI.service;

import java.util.Locale;

import com.example.qlytailieuAI.qlytailieuAI.dto.request.ChangePasswordRequest;
import com.example.qlytailieuAI.qlytailieuAI.dto.request.UserUpdateRequest;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.UserResponse;
import com.example.qlytailieuAI.qlytailieuAI.entity.NguoiDung;
import com.example.qlytailieuAI.qlytailieuAI.exception.AppException;
import com.example.qlytailieuAI.qlytailieuAI.exception.ErrorCode;
import com.example.qlytailieuAI.qlytailieuAI.mapper.UserMapper;
import com.example.qlytailieuAI.qlytailieuAI.repository.NguoiDungRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    NguoiDungRepository nguoiDungRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserResponse getMyInfo() {
        return userMapper.toUserResponse(getCurrentUser());
    }

    @Transactional
    public UserResponse updateMyInfo(UserUpdateRequest request) {
        NguoiDung user = getCurrentUser();

        if (request.getFullName() != null) {
            if (request.getFullName().isBlank()) {
                throw new AppException(ErrorCode.INVALID_REQUEST);
            }
            user.setHoTen(request.getFullName().trim());
        }

        if (request.getEmail() != null) {
            if (request.getEmail().isBlank()) {
                throw new AppException(ErrorCode.INVALID_REQUEST);
            }
            String email = request.getEmail().trim().toLowerCase(Locale.ROOT);
            if (nguoiDungRepository.existsByEmailIgnoreCaseAndMaNguoiDungNot(email, user.getMaNguoiDung())) {
                throw new AppException(ErrorCode.USER_EXISTED);
            }
            user.setEmail(email);
        }

        try {
            return userMapper.toUserResponse(nguoiDungRepository.save(user));
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
    }

    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        NguoiDung user = getCurrentUser();
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getMatKhau())) {
            throw new AppException(ErrorCode.PASSWORD_INVALID);
        }
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new AppException(ErrorCode.PASSWORD_CONFIRMATION_INVALID);
        }
        if (passwordEncoder.matches(request.getNewPassword(), user.getMatKhau())) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        user.setMatKhau(passwordEncoder.encode(request.getNewPassword()));
        nguoiDungRepository.save(user);
    }

    private NguoiDung getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return nguoiDungRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }
}
