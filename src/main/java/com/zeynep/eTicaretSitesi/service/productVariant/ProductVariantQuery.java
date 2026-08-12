package com.zeynep.eTicaretSitesi.service.productVariant;

import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductVariantQuery {

    private final ProductVariantService productVariantService;

    public ProductVariantQuery(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<ProductVariantResponse> getProductVariants() {
        return productVariantService.getAll();
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ProductVariantResponse getProductVariantById(@Argument Long id) {
        return productVariantService.getById(id);
    }
}