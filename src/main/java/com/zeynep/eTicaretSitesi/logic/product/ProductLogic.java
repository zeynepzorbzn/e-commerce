package com.zeynep.eTicaretSitesi.logic.product;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.Product;
import com.zeynep.eTicaretSitesi.dto.product.input.ProductInput;
import com.zeynep.eTicaretSitesi.dto.product.response.ProductResponse;
import com.zeynep.eTicaretSitesi.mapper.product.ProductMapper;
import com.zeynep.eTicaretSitesi.repo.product.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductLogic extends BaseLogic<Product, Long, ProductRepository> {

    private final ProductMapper mapper;

    public ProductLogic(ProductRepository repository, ProductMapper mapper) {

        super(repository);
        this.mapper = mapper;
    }

    public Product createProduct(ProductInput input) {
        return mapper.toEntity(input);
    }

    public ProductResponse toResponse(Product product) {
        return mapper.toResponse(product);
    }

}