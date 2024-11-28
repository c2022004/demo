package com.example.projectsale.image;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.exception.ApiRequestException;
import com.example.projectsale.utils.base.BaseUrlServiceUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageService.class);

    private final BaseUrlServiceUtil baseUrlServiceUtil;

    @Value("${server.servlet.context-path}")
    private String basePath;

    private final String imagePath = getImagePath();

    public String getImagePath() {
        String path = "D:/TestUploadImage/";
        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            System.out.println("Operating System: Windows");
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            System.out.println("Operating System: Linux or macOS");
            path = "/opt/upload/image/";
        } else {
            System.out.println("Operating System: Unknown");
            path = "/opt/upload/image/";
        }
        return path;
    }

    public String saveImage(MultipartFile file) {
        // Get the original filename and validate the extension
        String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
        String extension = getExtension(originalFilename);

        // Check if the file has a valid extension
        if (!isValidImageExtension(extension)) {
            throw new RuntimeException("Invalid file type. Only .png and .img files are allowed.");
        }

        // Generate a new filename with a UUID
        String filename = UUID.randomUUID() + extension;
        try {
            String filePath = imagePath + filename;
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        return baseUrlServiceUtil.getBaseUrl() + basePath + SystemConstant.API_PUBLIC + SystemConstant.VERSION_ONE + "/image/" + filename;
    }

    private boolean isValidImageExtension(String extension) {
        return extension.equalsIgnoreCase(".png")
                || extension.equalsIgnoreCase(".img")
                || extension.equalsIgnoreCase(".jpg")
                || extension.equalsIgnoreCase(".jpeg")
                || extension.equalsIgnoreCase(".gif")
                || extension.equalsIgnoreCase(".bmp");
    }

    public byte[] loadImage(String filename) {
        try {
            String filePath = imagePath + filename;
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiRequestException("FI_001");
        }
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.'));
    }

}
