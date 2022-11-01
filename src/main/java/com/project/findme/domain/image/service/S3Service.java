package com.project.findme.domain.image.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Service {

    List<String> upload(List<MultipartFile> multipartFiles, String dirName);

    void deleteFile(String fileName);

    String createFileName(String fileName);

    String getFileExtension(String fileName);
}
