package com.example.qlytailieuAI.qlytailieuAI.service;

import com.example.qlytailieuAI.qlytailieuAI.dto.request.FolderCreationRequest;
import com.example.qlytailieuAI.qlytailieuAI.dto.request.FolderUpdateRequest;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.FileinFolderResponse;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.FolderResponse;
import com.example.qlytailieuAI.qlytailieuAI.entity.Folder;
import com.example.qlytailieuAI.qlytailieuAI.entity.User;
import com.example.qlytailieuAI.qlytailieuAI.exception.AppException;
import com.example.qlytailieuAI.qlytailieuAI.exception.ErrorCode;
import com.example.qlytailieuAI.qlytailieuAI.mapper.FolderMapper;
import com.example.qlytailieuAI.qlytailieuAI.repository.DocumentRepository;
import com.example.qlytailieuAI.qlytailieuAI.repository.FolderRepository;
import com.example.qlytailieuAI.qlytailieuAI.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FolderService {
    FolderRepository folderRepository;
    UserRepository userRepository;
    DocumentRepository documentRepository;
    FolderMapper folderMapper;
    CurrentUserService currentUserService;

    @Transactional
    public FolderResponse createFolder(FolderCreationRequest request) {
        User user = currentUserService.getCurrentUser();

        Folder parentFolder = null;

        if (request.getParentFolderId() != null) {
            parentFolder = getOwnedFolder(request.getParentFolderId(), user.getId());
        }

        Folder folder = new Folder();
        folder.setUser(user);
        folder.setParentFolder(parentFolder);
        folder.setName(request.getName().trim());
        folder.setCreatedAt(LocalDateTime.now());
        folder.setUpdatedAt(LocalDateTime.now());

        folder = folderRepository.save(folder);

        log.info("Created folder with ID: {}", folder.getId());

        return folderMapper.toFolderResponse(folder);
    }

    @Transactional(readOnly = true)
    public List<FolderResponse> getFolders() {
        User user = currentUserService.getCurrentUser();
        return folderRepository
                .findAllByUser_IdOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(folderMapper::toFolderResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public FolderResponse getFolder(Integer folderId) {
        User user = currentUserService.getCurrentUser();
        return folderMapper.toFolderResponse(getOwnedFolder(folderId, user.getId()));
    }

    @Transactional
    public FolderResponse updateFolder(Integer folderId, FolderUpdateRequest request) {
        User user = currentUserService.getCurrentUser();
        Folder folder = getOwnedFolder(folderId, user.getId());

        if (request.getName() != null) {
            if (request.getName().isBlank()) {
                throw new AppException(ErrorCode.INVALID_FOLDER_NAME);
            }
            folder.setName(request.getName().trim());
        }

        if (Boolean.TRUE.equals(request.getMoveToRoot())) {
            folder.setParentFolder(null);
        } else if (request.getParentFolderId() != null) {
            Folder newParent = getOwnedFolder(request.getParentFolderId(), user.getId());
            validateFolderMove(folder, newParent);
            folder.setParentFolder(newParent);
        }

        folder.setUpdatedAt(LocalDateTime.now());

        return folderMapper.toFolderResponse(folderRepository.save(folder));
    }

    @Transactional
    public void deleteFolder(Integer folderId) {
        User user = currentUserService.getCurrentUser();
        Folder folder = getOwnedFolder(folderId, user.getId());

        if (folderRepository.existsByParentFolder_Id(folderId)) {
            throw new AppException(ErrorCode.FOLDER_NOT_EMPTY);
        }

        if (documentRepository.existsByFolder_Id(folderId)) {
            throw new AppException(ErrorCode.FOLDER_NOT_EMPTY);
        }

        folderRepository.delete(folder);
        log.info("Deleted folder with ID: {}", folderId);
    }

    @Transactional(readOnly = true)
    public List<FileinFolderResponse> getDocumentsInFolder(Integer folderId) {
        User user = currentUserService.getCurrentUser();
        getOwnedFolder(folderId, user.getId());
        return documentRepository
                .findAllByFolder_IdOrderByUploadedAtDesc(folderId)
                .stream()
                .map(folderMapper::toFileResponse)
                .toList();
    }

    private Folder getOwnedFolder(Integer folderId, Integer userId) {
        return folderRepository.findByIdAndUser_Id(folderId, userId)
                .orElseThrow(() -> new AppException(ErrorCode.FOLDER_NOT_EXISTED));
    }

    private void validateFolderMove(Folder folder, Folder newParent) {
        Folder current = newParent;
        while (current != null) {
            if (current.getId().equals(folder.getId())) {
                throw new AppException(ErrorCode.INVALID_FOLDER_PARENT);
            }
            current = current.getParentFolder();
        }
    }
}
