package com.zeynep.eTicaretSitesi.service.productVariant;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.Product;
import com.zeynep.eTicaretSitesi.core.entity.ProductVariant;
import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantInput;
import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantResponse;
import com.zeynep.eTicaretSitesi.logic.product.ProductLogic;
import com.zeynep.eTicaretSitesi.logic.productVariant.ProductVariantLogic;
import com.zeynep.eTicaretSitesi.mapper.productVariant.ProductVariantMapper;
import com.zeynep.eTicaretSitesi.repo.productVariant.ProductVariantRepository;
import org.springframework.stereotype.Service;

@Service
 public class ProductVariantService extends BaseService<ProductVariant, ProductVariantInput, Long, ProductVariantLogic, ProductVariantMapper,
        ProductVariantRepository, ProductVariantResponse> {

 private final ProductLogic productLogic;

    public ProductVariantService(ProductVariantRepository repository, ProductVariantLogic logic, ProductVariantMapper mapper, ProductLogic productLogic) {
         super(repository, logic, mapper);
         this.productLogic = productLogic;
    }
     @Override
     public ProductVariantResponse create(ProductVariantInput input) {

         ProductVariant variant = logic.createProductVariant(input);
         Product product = productLogic.findById(input.getProductId()).orElseThrow(() -> new RuntimeException("Ürün bulunamadı."));
         variant.setProduct(product);
         ProductVariant savedVariant = repository.save(variant);

         return logic.toResponse(savedVariant);
     }

}
