package com.zeynep.eTicaretSitesi.service.brand;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.Brand;
import com.zeynep.eTicaretSitesi.dto.brand.BrandInput;
import com.zeynep.eTicaretSitesi.dto.brand.BrandResponse;
import com.zeynep.eTicaretSitesi.logic.brand.BrandLogic;
import com.zeynep.eTicaretSitesi.mapper.brand.BrandMapper;
import com.zeynep.eTicaretSitesi.repo.brand.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandService extends BaseService<Brand, BrandInput, Long, BrandLogic, BrandMapper, BrandRepository, BrandResponse>{

    public BrandService(BrandRepository repository, BrandLogic logic, BrandMapper mapper ){

        super(repository, logic, mapper);
    }
    @Override
    public BrandResponse create(BrandInput input) {

        Brand brand = logic.createBrand(input);
        Brand savedBrand = repository.save(brand);

        return logic.toResponse(savedBrand);
    }

}
