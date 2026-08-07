package com.zeynep.eTicaretSitesi.mapper.product;

import com.zeynep.eTicaretSitesi.core.entity.Product;
import com.zeynep.eTicaretSitesi.dto.product.input.ProductInput;
import com.zeynep.eTicaretSitesi.dto.product.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductInput input) {

        Product product = new Product();

        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setGender(input.getGender());
        product.setSeason(input.getSeason());

        return product;
    }
    public ProductResponse toResponse(Product product) {

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setGender(product.getGender());
        response.setSeason(product.getSeason());
        response.setStoreName(product.getStore().getName());
        response.setBrandName(product.getBrand().getName());
        response.setCategoryName(product.getCategory().getName());

        return response;
    }
}