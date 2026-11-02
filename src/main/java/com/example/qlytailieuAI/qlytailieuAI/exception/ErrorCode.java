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
    INVALID_FOLDER_NAME(1014, "Thư mục ko hợp lệ", HttpStatus.BAD_REQUEST),
    FOLDER_NOT_EMPTY(1015, "Thư mục ko được để trống", HttpStatus.BAD_REQUEST),
    FOLDER_NOT_EXISTED(1016, "Thư mục ko tồn tại", HttpStatus.NOT_FOUND),
    INVALID_FOLDER_PARENT(1017, "Thư mục cha ko hợp lệ", HttpStatus.NOT_FOUND),
    INTERNAL_ERROR(1500, "Đã xảy ra lỗi hệ thống", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_DOCUMENT_TITLE(1018,"Tên tài liệu không hợp lệ",HttpStatus.BAD_REQUEST),
DOCUMENT_NOT_EXISTED(
        1019,
        "Không tìm thấy tài liệu",
        HttpStatus.NOT_FOUND
),

DOCUMENT_VERSION_NOT_EXISTED(
        1020,
        "Không tìm thấy phiên bản tài liệu",
        HttpStatus.NOT_FOUND
),

INVALID_FILE(
        1021,
        "File không hợp lệ",
        HttpStatus.BAD_REQUEST
),

FILE_TOO_LARGE(
        1022,
        "Kích thước file vượt quá giới hạn cho phép",
        HttpStatus.BAD_REQUEST
),

FILE_UPLOAD_FAILED(
        1023,
        "Không thể tải file lên",
        HttpStatus.INTERNAL_SERVER_ERROR
),

FILE_NOT_EXISTED(
        1024,
        "Không tìm thấy file",
        HttpStatus.NOT_FOUND
),

FILE_PROCESSING_FAILED(
        1025,
        "Không thể xử lý file",
        HttpStatus.INTERNAL_SERVER_ERROR
),

FILE_DELETE_FAILED(
        1026,
        "Không thể xóa file",
        HttpStatus.INTERNAL_SERVER_ERROR
),

DUPLICATE_DOCUMENT_VERSION(
        1027,
        "Nội dung file trùng với phiên bản hiện tại",
        HttpStatus.CONFLICT
),INVALID_CATEGORY_NAME(1028, "Category name is invalid", HttpStatus.BAD_REQUEST),
CATEGORY_NOT_EXISTED(1029, "Category does not exist", HttpStatus.NOT_FOUND),
CATEGORY_NAME_EXISTED(1030, "Category name already exists", HttpStatus.CONFLICT),
CATEGORY_HAS_CHILDREN(1031, "Category has child categories", HttpStatus.CONFLICT),
CATEGORY_IN_USE(1032, "Category is being used by documents", HttpStatus.CONFLICT),
INVALID_CATEGORY_PARENT(1033, "Invalid parent category", HttpStatus.BAD_REQUEST),
    AI_SERVICE_UNAVAILABLE(1034, "Dịch vụ AI không khả dụng", HttpStatus.SERVICE_UNAVAILABLE),
    NOTIFICATION_NOT_EXISTED(1035, "Không tìm thấy thông báo", HttpStatus.NOT_FOUND),
    REVIEW_NOT_EXISTED(1036, "Không tìm thấy yêu cầu kiểm duyệt", HttpStatus.NOT_FOUND),
    REVIEW_ALREADY_PROCESSED(1037, "Yêu cầu kiểm duyệt đã được xử lý", HttpStatus.CONFLICT),
    DOCUMENT_CONTENT_NOT_EXISTED(1038, "Nội dung tài liệu ko tồn tại", HttpStatus.CONFLICT);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
