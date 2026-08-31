package com.zeynep.eTicaretSitesi.mapper.productImage;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.ProductImage;
import com.zeynep.eTicaretSitesi.dto.productImage.ProductImageInput;
import com.zeynep.eTicaretSitesi.dto.productImage.ProductImageResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductImageMapper
        extends BaseMapper<ProductImage, ProductImageInput, ProductImageResponse> {

    @Override
    public ProductImage toEntity(ProductImageInput input) {

        ProductImage image = new ProductImage();

        return image;
    }

    @Override
    public ProductImageResponse toResponse(ProductImage image) {

        ProductImageResponse response = new ProductImageResponse();

        response.setId(image.getId());
        response.setImageToken(image.getImageToken());

        if (image.getProduct() != null) {
            response.setProductId(image.getProduct().getId());
        }

        return response;
    }

    @Override
    public void updateEntity(
            ProductImage image,
            ProductImageInput input
    ) {
        // Şimdilik güncellenecek alan yok.
    }

    @Override
    public List<ProductImageResponse> toResponseList(
            List<ProductImage> images
    ) {
        return images.stream()
                .map(this::toResponse)
                .toList();
    }
}