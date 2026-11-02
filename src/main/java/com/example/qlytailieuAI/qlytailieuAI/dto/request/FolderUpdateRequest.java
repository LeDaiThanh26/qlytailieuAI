package com.example.qlytailieuAI.qlytailieuAI.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderUpdateRequest {
    @Size(max = 150)
    String name;
    Integer parentFolderId;
    Boolean moveToRoot;
}
