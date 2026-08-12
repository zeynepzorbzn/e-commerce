package com.zeynep.eTicaretSitesi.logic.productVariant;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.ProductVariant;
import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantInput;
import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantResponse;
import com.zeynep.eTicaretSitesi.mapper.productVariant.ProductVariantMapper;
import com.zeynep.eTicaretSitesi.repo.productVariant.ProductVariantRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductVariantLogic extends BaseLogic<ProductVariant, Long, ProductVariantRepository> {

    private final ProductVariantMapper mapper;

    public ProductVariantLogic(ProductVariantRepository repository,  ProductVariantMapper mapper){
        super(repository);
        this.mapper= mapper;
    }
    public ProductVariant createProductVariant(ProductVariantInput input){
        return mapper.toEntity(input);
    }
    public ProductVariantResponse toResponse(ProductVariant variant){
        return mapper.toResponse(variant);
    }

}
