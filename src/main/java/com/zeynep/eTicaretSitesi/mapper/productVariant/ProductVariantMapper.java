package com.zeynep.eTicaretSitesi.mapper.productVariant;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.ProductVariant;
import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantInput;
import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductVariantMapper extends BaseMapper<ProductVariant, ProductVariantInput, ProductVariantResponse> {

    @Override
    public ProductVariant toEntity(ProductVariantInput input){

        ProductVariant variant = new ProductVariant();
        variant.setSize(input.getSize());
        variant.setStock(input.getStock());
        variant.setColor(input.getColor());

        return variant;
    }
    @Override
    public ProductVariantResponse toResponse(ProductVariant variant) {

        ProductVariantResponse response = new ProductVariantResponse();

        response.setId(variant.getId());
        response.setSize(variant.getSize());
        response.setStock(variant.getStock());
        response.setColor(variant.getColor());

        response.setProductId(variant.getProduct().getId());
        response.setProductName(variant.getProduct().getName());

        return response;
    }
    @Override
    public void updateEntity(ProductVariant variant, ProductVariantInput input) {

        variant.setSize(input.getSize());
        variant.setStock(input.getStock());
        variant.setColor(input.getColor());
    }
    @Override
    public List<ProductVariantResponse> toResponseList(List<ProductVariant> variants) {
        return variants.stream().map(this::toResponse).toList();
    }





}
