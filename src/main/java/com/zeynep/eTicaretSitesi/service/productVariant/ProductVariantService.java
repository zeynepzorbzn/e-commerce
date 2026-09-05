package com.zeynep.eTicaretSitesi.service.productVariant;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.Product;
import com.zeynep.eTicaretSitesi.core.entity.ProductVariant;
import com.zeynep.eTicaretSitesi.core.entity.Store;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.core.enums.RoleName;
import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantInput;
import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantResponse;
import com.zeynep.eTicaretSitesi.logic.product.ProductLogic;
import com.zeynep.eTicaretSitesi.logic.productVariant.ProductVariantLogic;
import com.zeynep.eTicaretSitesi.mapper.productVariant.ProductVariantMapper;
import com.zeynep.eTicaretSitesi.repo.productVariant.ProductVariantRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProductVariantService extends BaseService<
        ProductVariant,
        ProductVariantInput,
        Long,
        ProductVariantLogic,
        ProductVariantMapper,
        ProductVariantRepository,
        ProductVariantResponse> {

    private final ProductLogic productLogic;

    public ProductVariantService(
            ProductVariantRepository repository,
            ProductVariantLogic logic,
            ProductVariantMapper mapper,
            ProductLogic productLogic
    ) {
        super(repository, logic, mapper);
        this.productLogic = productLogic;
    }

    @Override
    public ProductVariantResponse create(ProductVariantInput input) {

        User user = getCurrentUser();

        Product product = productLogic.findById(input.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Ürün bulunamadı.")
                );

        checkProductAccess(product, user);

        ProductVariant variant = logic.createProductVariant(input);
        variant.setProduct(product);

        ProductVariant savedVariant = repository.save(variant);

        return logic.toResponse(savedVariant);
    }

    @Override
    public ProductVariantResponse update(Long id, ProductVariantInput input) {

        User user = getCurrentUser();

        ProductVariant variant = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Seçenek bulunamadı.")
                );

        Product product = variant.getProduct();

        if (product == null) {
            throw new RuntimeException("Seçeneğe ait ürün bulunamadı.");
        }

        checkProductAccess(product, user);

        mapper.updateEntity(variant, input);

        ProductVariant updatedVariant = repository.save(variant);

        return mapper.toResponse(updatedVariant);
    }

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                authentication.getPrincipal() == null) {

            throw new RuntimeException("Kullanıcı bulunamadı.");
        }

        return (User) authentication.getPrincipal();
    }

    private void checkProductAccess(Product product, User user) {

        RoleName role = user.getRole().getName();

        if (role == RoleName.ADMIN) {
            return;
        }

        if (role != RoleName.STORE_MANAGER) {
            throw new RuntimeException("Bu işlem için yetkiniz yok.");
        }

        Store store = product.getStore();

        if (store == null ||
                store.getOwner() == null ||
                !store.getOwner().getId().equals(user.getId())) {

            throw new RuntimeException(
                    "Bu ürüne seçenek ekleme/düzenleme yetkiniz yok."
            );
        }
    }
}
