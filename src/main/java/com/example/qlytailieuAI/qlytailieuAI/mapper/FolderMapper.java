package com.example.qlytailieuAI.qlytailieuAI.mapper;

import com.example.qlytailieuAI.qlytailieuAI.dto.response.FileinFolderResponse;
import com.example.qlytailieuAI.qlytailieuAI.dto.response.FolderResponse;
import com.example.qlytailieuAI.qlytailieuAI.entity.Document;
import com.example.qlytailieuAI.qlytailieuAI.entity.Folder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FolderMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "parentFolder.id", target = "parentFolderId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    FolderResponse toFolderResponse(Folder folder);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "documentType", target = "documentType")
    FileinFolderResponse toFileResponse(Document document);
}
