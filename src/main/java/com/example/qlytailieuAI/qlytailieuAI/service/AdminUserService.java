package com.example.qlytailieuAI.qlytailieuAI.service;

import java.util.List;
import java.util.Locale;

import com.example.qlytailieuAI.qlytailieuAI.dto.request.UpdateRoleRequest;
import com.example.qlytailieuAI.qlytailieuAI.dto.request.UpdateStatusRequest;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.UserResponse;
import com.example.qlytailieuAI.qlytailieuAI.entity.NguoiDung;
import com.example.qlytailieuAI.qlytailieuAI.entity.VaiTro;
import com.example.qlytailieuAI.qlytailieuAI.exception.AppException;
import com.example.qlytailieuAI.qlytailieuAI.exception.ErrorCode;
import com.example.qlytailieuAI.qlytailieuAI.mapper.UserMapper;
import com.example.qlytailieuAI.qlytailieuAI.repository.NguoiDungRepository;
import com.example.qlytailieuAI.qlytailieuAI.repository.VaiTroRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class AdminUserService {
    NguoiDungRepository nguoiDungRepository;
    VaiTroRepository vaiTroRepository;
    UserMapper userMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        log.info("Admin requested user list");
        return nguoiDungRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Transactional
    public UserResponse updateStatus(Integer userId, UpdateStatusRequest request) {
        NguoiDung user = findUser(userId);
        String status = request.getStatus().trim().toUpperCase(Locale.ROOT);
        if (!"ACTIVE".equals(status) && !"LOCKED".equals(status)) {
            throw new AppException(ErrorCode.INVALID_STATUS);
        }

        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if ("LOCKED".equals(status) && user.getEmail().equalsIgnoreCase(currentEmail)) {
            throw new AppException(ErrorCode.CANNOT_LOCK_SELF);
        }

        user.setTrangThai(status);
        return userMapper.toUserResponse(nguoiDungRepository.save(user));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Transactional
    public UserResponse updateRole(Integer userId, UpdateRoleRequest request) {
        NguoiDung user = findUser(userId);
        VaiTro role = vaiTroRepository.findById(request.getRoleId())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        user.setVaiTro(role);
        return userMapper.toUserResponse(nguoiDungRepository.save(user));
    }

    private NguoiDung findUser(Integer userId) {
        return nguoiDungRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }
}
