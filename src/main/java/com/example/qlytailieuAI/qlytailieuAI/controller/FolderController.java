package com.example.qlytailieuAI.qlytailieuAI.controller;

import com.example.qlytailieuAI.qlytailieuAI.dto.request.ApiResponse;
import com.example.qlytailieuAI.qlytailieuAI.dto.request.FolderCreationRequest;
import com.example.qlytailieuAI.qlytailieuAI.dto.request.FolderUpdateRequest;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.FileinFolderResponse;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.FolderResponse;
import com.example.qlytailieuAI.qlytailieuAI.service.FolderService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FolderController {
    FolderService folderService;

    @PostMapping
    public ApiResponse<FolderResponse> createFolder(@RequestBody @Valid FolderCreationRequest request) {
        return ApiResponse.<FolderResponse>builder()
                .data(folderService.createFolder(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<FolderResponse>> getFolders() {
        return ApiResponse.<List<FolderResponse>>builder()
                .data(folderService.getFolders())
                .build();
    }

    @GetMapping("/{folderId}")
    public ApiResponse<FolderResponse> getFolder(@PathVariable Integer folderId) {
        return ApiResponse.<FolderResponse>builder()
                .data(folderService.getFolder(folderId))
                .build();
    }

    @PatchMapping("/{folderId}")
    public ApiResponse<FolderResponse> updateFolder(
            @PathVariable Integer folderId,
            @RequestBody @Valid FolderUpdateRequest request) {
        return ApiResponse.<FolderResponse>builder()
                .data(folderService.updateFolder(folderId, request))
                .build();
    }

    @DeleteMapping("/{folderId}")
    public ApiResponse<Void> deleteFolder(@PathVariable Integer folderId) {
        folderService.deleteFolder(folderId);
        return ApiResponse.<Void>builder()
            .message("Folder deleted successfully")
            .build();
    }

    @GetMapping("/{folderId}/documents")
    public ApiResponse<List<FileinFolderResponse>> getDocumentsInFolder(@PathVariable Integer folderId) {
        return ApiResponse.<List<FileinFolderResponse>>builder()
                .data(folderService.getDocumentsInFolder(folderId))
                .build();
    }
}
