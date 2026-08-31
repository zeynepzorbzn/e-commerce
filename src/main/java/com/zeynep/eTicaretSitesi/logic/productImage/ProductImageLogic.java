package com.zeynep.eTicaretSitesi.logic.productImage;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.ProductImage;
import com.zeynep.eTicaretSitesi.dto.productImage.ProductImageInput;
import com.zeynep.eTicaretSitesi.dto.productImage.ProductImageResponse;
import com.zeynep.eTicaretSitesi.mapper.productImage.ProductImageMapper;
import com.zeynep.eTicaretSitesi.repo.productImage.ProductImageRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductImageLogic
        extends BaseLogic<ProductImage, Long, ProductImageRepository> {

    private final ProductImageMapper mapper;

    public ProductImageLogic(
            ProductImageRepository repository,
            ProductImageMapper mapper
    ) {
        super(repository);
        this.mapper = mapper;
    }

    public ProductImage createProductImage(ProductImageInput input) {
        return mapper.toEntity(input);
    }

    public ProductImageResponse toResponse(ProductImage image) {
        return mapper.toResponse(image);
    }

    public List<ProductImage> getByProductId(Long productId) {
        return repository.findByProduct_Id(productId);
    }
}