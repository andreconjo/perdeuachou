package br.com.perdeuachou.api.controller;

import br.com.perdeuachou.api.service.StorageService;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {


    @Autowired
    private StorageService storageService;

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile multipartFile) {
        System.out.println("File upload in progress.");
        String path = "";
        try {
            final File file = storageService.convertMultiPartFileToFile(multipartFile);
            path = storageService.uploadFileToS3Bucket(file);
            System.out.println("File upload is completed.");
            file.delete();  // To remove the file locally created in the project folder
        } catch (final AmazonServiceException ex) {
            System.out.println("File upload is failed.");
            System.out.println("Error= {} while uploading file." + ex.getMessage());
        }

        return new ResponseEntity<>(path, HttpStatus.OK);
    }
    @PostMapping("/uploadFiles")
    public ResponseEntity<List<String>> uploadFile(@RequestPart(value = "file") List<MultipartFile> multipartFile) {
        List<String> paths = storageService.uploadFilesToS3Bucket(multipartFile);
        return new ResponseEntity<>(paths, HttpStatus.OK);
    }


//    @DeleteMapping("/deleteFile")
//    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
//        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
//    }
}