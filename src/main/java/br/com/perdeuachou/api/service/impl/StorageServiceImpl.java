package br.com.perdeuachou.api.service.impl;

import br.com.perdeuachou.api.config.AWSS3Config;
import br.com.perdeuachou.api.service.StorageService;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Autowired
    AWSS3Config awss3Config;


    @Override
    public List<String> uploadFilesToS3Bucket(List<MultipartFile> multipartFiles) {
        List<String> urls = new ArrayList<>();

        multipartFiles.forEach(multipartFile -> {
            try {
                final File file = this.convertMultiPartFileToFile(multipartFile);
                urls.add(this.uploadFileToS3Bucket(file));
                System.out.println("File upload is completed.");
                file.deleteOnExit();
            } catch (final AmazonServiceException ex) {
                System.out.println("File upload is failed.");
                System.out.println("Error= {} while uploading file." + ex.getMessage());
            }
        });

        return urls;

    }

    @Override
    public String uploadFileToS3Bucket(final File file) {
        final String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
        System.out.println("Uploading file with name= " + uniqueFileName);
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file).withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);

        AmazonS3Client s3Client = (AmazonS3Client) awss3Config.getAmazonS3Cient();

        URL url = s3Client.getUrl(bucketName, uniqueFileName);

        return url.getProtocol() + "://" + url.getAuthority() + url.getPath();
    }

    @Override
    public File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            System.out.println("Error converting the multi-part file to file= " + ex.getMessage());
        }
        return file;
    }
}
