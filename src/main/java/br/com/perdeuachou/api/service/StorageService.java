package br.com.perdeuachou.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface StorageService {

    List<String> uploadFilesToS3Bucket(List<MultipartFile> multipartFiles);

    String uploadFileToS3Bucket(File file);

    File convertMultiPartFileToFile(MultipartFile multipartFile);
}
