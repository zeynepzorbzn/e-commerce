package com.zeynep.eTicaretSitesi.service.product;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.Brand;
import com.zeynep.eTicaretSitesi.core.entity.Category;
import com.zeynep.eTicaretSitesi.core.entity.Product;
import com.zeynep.eTicaretSitesi.core.entity.Store;
import com.zeynep.eTicaretSitesi.dto.product.input.ProductInput;
import com.zeynep.eTicaretSitesi.dto.product.response.ProductResponse;
import com.zeynep.eTicaretSitesi.logic.brand.BrandLogic;
import com.zeynep.eTicaretSitesi.logic.category.CategoryLogic;
import com.zeynep.eTicaretSitesi.logic.product.ProductLogic;
import com.zeynep.eTicaretSitesi.logic.store.StoreLogic;
import com.zeynep.eTicaretSitesi.mapper.product.ProductMapper;
import com.zeynep.eTicaretSitesi.repo.product.ProductRepository;
import org.springframework.stereotype.Service;

 @Service
 public class ProductService extends BaseService <Product, ProductInput, Long, ProductLogic, ProductMapper, ProductRepository, ProductResponse>  {

     private final BrandLogic brandLogic;
     private final CategoryLogic categoryLogic;
     private final StoreLogic storeLogic;

     public ProductService(ProductRepository repository, ProductLogic logic, ProductMapper mapper, BrandLogic brandLogic,
                           CategoryLogic categoryLogic, StoreLogic storeLogic){

         super(repository, logic, mapper);
         this.brandLogic = brandLogic;
         this.categoryLogic = categoryLogic;
         this.storeLogic = storeLogic;

     }
     @Override
     public ProductResponse create(ProductInput input) {

         Product product = logic.createProduct(input);

         Brand brand = brandLogic.findById(input.getBrandId()).orElseThrow(() -> new RuntimeException("Marka bulunamadı."));
         Category category = categoryLogic.findById(input.getCategoryId()).orElseThrow(() -> new RuntimeException("Kategori bulunamadı."));
         Store store = storeLogic.findById(input.getStoreId()).orElseThrow(() -> new RuntimeException("Mağaza bulunamadı."));

         product.setBrand(brand);
         product.setCategory(category);
         product.setStore(store);

         Product savedProduct = repository.save(product);
         return logic.toResponse(savedProduct);
     }
 }
