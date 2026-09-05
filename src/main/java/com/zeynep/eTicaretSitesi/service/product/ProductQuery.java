package com.zeynep.eTicaretSitesi.service.product;

import com.zeynep.eTicaretSitesi.dto.product.response.ProductResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductQuery {

    private final ProductService productService;

    public ProductQuery(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public ProductResponse getProduct(@Argument Long id) {
        return productService.getById(id);
    }

    @QueryMapping
    public List<ProductResponse> getProducts() {
        return productService.getAll();
    }

    @QueryMapping
    public List<ProductResponse> getProductsByCategory(@Argument Long categoryId) {
        return productService.getByCategoryId(categoryId);
    }
    @QueryMapping
    @PreAuthorize("hasRole('STORE_MANAGER')")
    public List<ProductResponse> getMyStoreProducts() {
        return productService.getMyStoreProducts();
    }
}