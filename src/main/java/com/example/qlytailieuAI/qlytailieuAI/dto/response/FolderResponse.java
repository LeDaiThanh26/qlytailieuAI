package com.example.qlytailieuAI.qlytailieuAI.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FolderResponse {
    private Integer id;
    private Integer userId;
    private Integer parentFolderId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
