package com.example.qlytailieuAI.qlytailieuAI.service;

import java.util.Locale;

import com.example.qlytailieuAI.qlytailieuAI.dto.request.ChangePasswordRequest;
import com.example.qlytailieuAI.qlytailieuAI.dto.request.UserUpdateRequest;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.UserResponse;
import com.example.qlytailieuAI.qlytailieuAI.entity.User;
import com.example.qlytailieuAI.qlytailieuAI.exception.AppException;
import com.example.qlytailieuAI.qlytailieuAI.exception.ErrorCode;
import com.example.qlytailieuAI.qlytailieuAI.mapper.UserMapper;
import com.example.qlytailieuAI.qlytailieuAI.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
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

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    CurrentUserService currentUserService;

    @Transactional(readOnly = true)
    public UserResponse getMyInfo() {
        User user = currentUserService.getCurrentUser();
        return userMapper.toUserResponse(user);
    }

    @Transactional
    public UserResponse updateMyInfo(UserUpdateRequest request) {
        User user = currentUserService.getCurrentUser();

        if (request.getFullName() != null) {
            if (request.getFullName().isBlank()) {
                throw new AppException(ErrorCode.INVALID_REQUEST);
            }
            user.setFullName(request.getFullName().trim());
        }

        if (request.getEmail() != null) {
            if (request.getEmail().isBlank()) {
                throw new AppException(ErrorCode.INVALID_REQUEST);
            }
            String email = request.getEmail().trim().toLowerCase(Locale.ROOT);
            if (userRepository.existsByEmailIgnoreCaseAndIdNot(email, user.getId())) {
                throw new AppException(ErrorCode.USER_EXISTED);
            }
            user.setEmail(email);
        }

        try {
            return userMapper.toUserResponse(userRepository.save(user));
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
    }

    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        User user = currentUserService.getCurrentUser();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.PASSWORD_INVALID);
        }
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new AppException(ErrorCode.PASSWORD_CONFIRMATION_INVALID);
        }
        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
