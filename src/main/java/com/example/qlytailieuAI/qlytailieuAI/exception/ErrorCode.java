package com.example.qlytailieuAI.qlytailieuAI.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001, "Email đã được sử dụng", HttpStatus.CONFLICT),
    USER_NOT_EXISTED(1002, "Không tìm thấy người dùng", HttpStatus.NOT_FOUND),
    ROLE_NOT_EXISTED(1003, "Không tìm thấy vai trò", HttpStatus.NOT_FOUND),
    INVALID_CREDENTIALS(1004, "Email hoặc mật khẩu không đúng", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID(1005, "Token không hợp lệ hoặc đã hết hạn", HttpStatus.UNAUTHORIZED),
    ACCOUNT_LOCKED(1006, "Tài khoản đã bị khóa", HttpStatus.FORBIDDEN),
    PASSWORD_INVALID(1007, "Mật khẩu hiện tại không đúng", HttpStatus.BAD_REQUEST),
    PASSWORD_CONFIRMATION_INVALID(1008, "Mật khẩu xác nhận không khớp", HttpStatus.BAD_REQUEST),
    INVALID_STATUS(1009, "Trạng thái không hợp lệ", HttpStatus.BAD_REQUEST),
    CANNOT_LOCK_SELF(1010, "Không thể tự khóa tài khoản đang đăng nhập", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1011, "Bạn chưa đăng nhập hoặc phiên đăng nhập không hợp lệ", HttpStatus.UNAUTHORIZED),
    FORBIDDEN(1012, "Bạn không có quyền thực hiện thao tác này", HttpStatus.FORBIDDEN),
    INVALID_REQUEST(1013, "Dữ liệu yêu cầu không hợp lệ", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(1500, "Đã xảy ra lỗi hệ thống", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
