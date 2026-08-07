package com.zeynep.eTicaretSitesi.logic.brand;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.Brand;
import com.zeynep.eTicaretSitesi.dto.brand.BrandInput;
import com.zeynep.eTicaretSitesi.dto.brand.BrandResponse;
import com.zeynep.eTicaretSitesi.mapper.brand.BrandMapper;
import com.zeynep.eTicaretSitesi.repo.brand.BrandRepository;
import org.springframework.stereotype.Component;

@Component
public class BrandLogic extends BaseLogic<Brand, Long, BrandRepository> {

    private final BrandMapper mapper;

    public BrandLogic(BrandRepository repository, BrandMapper mapper){
        super(repository);
        this.mapper=mapper;
    }
    public Brand createBrand(BrandInput input){
        return mapper.toEntity(input);
    }
    public BrandResponse toResponse(Brand brand){
        return mapper.toResponse(brand);
    }

}
