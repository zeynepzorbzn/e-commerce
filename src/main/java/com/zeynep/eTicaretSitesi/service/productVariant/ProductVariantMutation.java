package com.zeynep.eTicaretSitesi.service.productVariant;

import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantInput;
import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class ProductVariantMutation {

    private final ProductVariantService productVariantService;

    public ProductVariantMutation(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }

    @MutationMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    public ProductVariantResponse createProductVariant(
            @Argument ProductVariantInput input) {

        return productVariantService.create(input);
    }

    @MutationMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    public ProductVariantResponse updateProductVariant(
            @Argument Long id,
            @Argument ProductVariantInput input) {

        return productVariantService.update(id, input);
    }

    @MutationMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    public Boolean deleteProductVariant(
            @Argument Long id) {

        productVariantService.delete(id);
        return true;
    }
}