package com.zeynep.eTicaretSitesi.service.product;

import com.zeynep.eTicaretSitesi.dto.product.input.ProductInput;
import com.zeynep.eTicaretSitesi.dto.product.response.ProductResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class ProductMutation {

    private final ProductService productService;

    public ProductMutation(ProductService productService) {
        this.productService = productService;
    }

    @MutationMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    public ProductResponse createProduct(@Argument ProductInput input) {
        return productService.create(input);
    }

    @MutationMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    public ProductResponse updateProduct(
            @Argument Long id,
            @Argument ProductInput input
    ) {
        return productService.updateProduct(id, input);
    }

    @MutationMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    public Boolean deleteProduct(@Argument Long id) {
        productService.delete(id);
        return true;
    }
}