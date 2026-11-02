package com.example.qlytailieuAI.qlytailieuAI.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderCreationRequest {
    @NotNull
    Integer parentFolderId;
    @NotBlank
    @Size(max = 150)
    String name;
}
