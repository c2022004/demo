package com.example.projectsale.imageproduct.service;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.enums.SystemEnumStatus;
import com.example.projectsale.image.ImageService;
import com.example.projectsale.imageproduct.entity.ImageProduct;
import com.example.projectsale.imageproduct.repo.ImageProductRepo;
import com.example.projectsale.product.entity.Product;
import com.example.projectsale.utils.AbsServiceUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageProductServiceImpl extends AbsServiceUtil implements ImageProductService {

    private final ImageService imageService;

    private final ImageProductRepo imageProductRepo;


    @Override
    public void createImageProduct(Product product, MultipartFile[] imageProduct) {
        List<ImageProduct> imageProducts = new ArrayList<>();
        Arrays.stream(imageProduct)
                .forEach(image -> {
                    String urlProduct = imageService.saveImage(image);
                    ImageProduct build = ImageProduct.builder()
                            .product(product)
                            .urlImage(urlProduct)
                            .status(SystemEnumStatus.ACTIVE)
                            .isDeleted(SystemConstant.IS_DELETED_ACTIVE)
                            .build();
                    imageProducts.add(build);
                });
        imageProductRepo.saveAll(imageProducts);
    }


}
