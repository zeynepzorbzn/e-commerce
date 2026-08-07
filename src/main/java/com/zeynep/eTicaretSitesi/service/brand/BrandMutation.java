package com.zeynep.eTicaretSitesi.service.brand;

import com.zeynep.eTicaretSitesi.dto.brand.BrandInput;
import com.zeynep.eTicaretSitesi.dto.brand.BrandResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class BrandMutation {

    private final BrandService brandService;

    public BrandMutation(BrandService brandService) {
        this.brandService = brandService;
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public BrandResponse createBrand(@Argument BrandInput input) {
        return brandService.create(input);
    }
}