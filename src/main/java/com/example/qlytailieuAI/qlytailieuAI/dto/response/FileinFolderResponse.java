package com.example.qlytailieuAI.qlytailieuAI.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileinFolderResponse {
    Integer id;
    String title;
    String status;
    String documentType;
}
