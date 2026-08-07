package com.zeynep.eTicaretSitesi.mapper.brand;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.Brand;
import com.zeynep.eTicaretSitesi.dto.brand.BrandInput;
import com.zeynep.eTicaretSitesi.dto.brand.BrandResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandMapper extends BaseMapper<Brand, BrandInput, BrandResponse> {

    public Brand toEntity(BrandInput input ){

        Brand brand = new Brand();
        brand.setName(input.getName());
        return brand;
    }
    public BrandResponse toResponse(Brand brand){
        BrandResponse response = new BrandResponse();
        response.setName(brand.getName());
        response.setId(brand.getId());
        return response;
    }
    @Override
    public void updateEntity(Brand brand, BrandInput input) {
        brand.setName(input.getName());
    }
    @Override
    public List<BrandResponse> toResponseList(List<Brand> brands) {
        return brands.stream()
                .map(this::toResponse)
                .toList();
    }
}
