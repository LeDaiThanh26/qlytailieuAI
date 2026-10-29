package com.example.qlytailieuAI.qlytailieuAI.mapper;

import com.example.qlytailieuAI.qlytailieuAI.dto.response.UserResponse;
import com.example.qlytailieuAI.qlytailieuAI.entity.NguoiDung;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    public UserResponse toUserResponse(NguoiDung user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .userId(user.getMaNguoiDung())
                .fullName(user.getHoTen())
                .email(user.getEmail())
                .status(user.getTrangThai())
                .roleId(user.getVaiTro() == null ? null : user.getVaiTro().getMaVaiTro())
                .roleName(user.getVaiTro() == null ? null : user.getVaiTro().getTenVaiTro())
                .createdAt(user.getNgayTao())
                .updatedAt(user.getNgayCapNhat())
                .build();
    }
}
