package com.example.projectsale.imageproduct.entity;

import com.example.projectsale.product.entity.Product;
import com.example.projectsale.utils.base.BaseEntityUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image_product")
public class ImageProduct extends BaseEntityUtil {

    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
