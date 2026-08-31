package com.zeynep.eTicaretSitesi.service.productImage;

import com.zeynep.eTicaretSitesi.core.entity.Product;
import com.zeynep.eTicaretSitesi.core.entity.ProductImage;
import com.zeynep.eTicaretSitesi.dto.productImage.ProductImageResponse;
import com.zeynep.eTicaretSitesi.logic.product.ProductLogic;
import com.zeynep.eTicaretSitesi.mapper.productImage.ProductImageMapper;
import com.zeynep.eTicaretSitesi.repo.productImage.ProductImageRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class ProductImageGraphQLMutation {

    private final ProductImageRepository productImageRepository;
    private final ProductImageMapper productImageMapper;
    private final ProductLogic productLogic;

    public ProductImageGraphQLMutation(
            ProductImageRepository productImageRepository,
            ProductImageMapper productImageMapper,
            ProductLogic productLogic
    ) {
        this.productImageRepository = productImageRepository;
        this.productImageMapper = productImageMapper;
        this.productLogic = productLogic;
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductImageResponse addProductImage(
            @Argument Long productId,
            @Argument String imageToken
    ) {

        Product product = productLogic.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Ürün bulunamadı.")
                );

        if (imageToken == null || imageToken.isBlank()) {
            throw new RuntimeException("Image token boş olamaz.");
        }

        ProductImage productImage = new ProductImage();

        productImage.setProduct(product);
        productImage.setImageToken(imageToken);

        ProductImage saved =
                productImageRepository.save(productImage);

        return productImageMapper.toResponse(saved);
    }
}