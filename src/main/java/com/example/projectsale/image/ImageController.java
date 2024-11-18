package com.example.projectsale.image;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.utils.base.BaseUrlServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(SystemConstant.API_PUBLIC + SystemConstant.VERSION_ONE + "/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    private final BaseUrlServiceUtil baseUrlServiceUtil;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile[] files) {
        List<String> urlImages = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = imageService.saveImage(file);
//            urlImages.add(baseUrlServiceUtil.getBaseUrl() + SystemConstant.API_PUBLIC + SystemConstant.VERSION_ONE + "/image/" + filename);
            urlImages.add(filename);
        }
        return new ResponseEntity<>(urlImages, HttpStatus.OK);
    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) {
        byte[] imageBytes = imageService.loadImage(filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}