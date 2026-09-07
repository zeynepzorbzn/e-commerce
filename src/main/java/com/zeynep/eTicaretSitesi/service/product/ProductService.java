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
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.core.enums.RoleName;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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

        // Product product = logic.updateProduct(input);

         Brand brand = brandLogic.findById(input.getBrandId()).orElseThrow(() -> new RuntimeException("Marka bulunamadı."));

         Category category = categoryLogic.findById(input.getCategoryId()).orElseThrow(() -> new RuntimeException("Kategori bulunamadı."));

         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         User user = (User) authentication.getPrincipal();
         Store store;

         if (user.getRole().getName() == RoleName.ADMIN) {

             store = storeLogic.findById(input.getStoreId())
                     .orElseThrow(() ->
                             new RuntimeException("Mağaza bulunamadı."));
         }
         else if (user.getRole().getName() == RoleName.STORE_MANAGER) {

             store = storeLogic.findByOwnerId(user.getId())
                     .orElseThrow(() -> new RuntimeException("Size ait mağaza bulunamadı."));
         } else {

             throw new RuntimeException("Bu işlem için yetkiniz yok.");
         }
         product.setBrand(brand);
         product.setCategory(category);
         product.setStore(store);
         Product savedProduct = repository.save(product);

         return logic.toResponse(savedProduct);
     }

     public List<ProductResponse> getByCategoryId(Long categoryId) {
         return logic.getByCategoryId(categoryId).stream().map(logic::toResponse).toList();

     }
    public List<ProductResponse> searchProducts(String query, Long categoryId) {

        String normalizedQuery = query == null
                ? ""
                : query.trim();

        if (normalizedQuery.isBlank()) {

            return categoryId == null
                    ? getAll()
                    : getByCategoryId(categoryId);
        }

        return repository.searchProducts(normalizedQuery, categoryId).stream().map(logic::toResponse).toList();
    }
    public List<ProductResponse> getMyStoreProducts() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user = (User) authentication.getPrincipal();

        Store store = storeLogic.findByOwnerId(user.getId())
                .orElseThrow(() ->
                        new RuntimeException("Size ait mağaza bulunamadı.")
                );

        return repository.findByStoreId(store.getId())
                .stream()
                .map(logic::toResponse)
                .toList();
    }

    public ProductResponse updateProduct(Long id, ProductInput input) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ürün bulunamadı."));

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        if (user.getRole().getName() == RoleName.STORE_MANAGER) {

            Store store = storeLogic.findByOwnerId(user.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Size ait mağaza bulunamadı."));

            if (!product.getStore().getId().equals(store.getId())) {
                throw new RuntimeException(
                        "Bu ürünü güncelleme yetkiniz yok."
                );
            }
        }

        Brand brand = brandLogic.findById(input.getBrandId())
                .orElseThrow(() ->
                        new RuntimeException("Marka bulunamadı."));

        Category category = categoryLogic.findById(input.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Kategori bulunamadı."));

        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setGender(input.getGender());
        product.setSeason(input.getSeason());
        product.setBrand(brand);
        product.setCategory(category);

        Product updated = repository.save(product);

        return logic.toResponse(updated);
    }


 }
