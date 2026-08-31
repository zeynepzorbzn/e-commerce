package com.zeynep.eTicaretSitesi.service.brand;

import com.zeynep.eTicaretSitesi.dto.brand.BrandResponse;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BrandQuery {

    private final BrandService brandService;

    public BrandQuery(BrandService brandService) {
        this.brandService = brandService;
    }

    @QueryMapping
    public List<BrandResponse> getBrands() {
        return brandService.getAll();
    }
}