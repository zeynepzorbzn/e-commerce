package com.zeynep.eTicaretSitesi.service.productImage;

import com.zeynep.eTicaretSitesi.core.entity.Product;
import com.zeynep.eTicaretSitesi.core.entity.ProductImage;
import com.zeynep.eTicaretSitesi.core.entity.Store;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.core.enums.RoleName;
import com.zeynep.eTicaretSitesi.dto.productImage.ProductImageResponse;
import com.zeynep.eTicaretSitesi.logic.product.ProductLogic;
import com.zeynep.eTicaretSitesi.mapper.productImage.ProductImageMapper;
import com.zeynep.eTicaretSitesi.repo.productImage.ProductImageRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.zeynep.eTicaretSitesi.core.entity.ProductVariant;
import com.zeynep.eTicaretSitesi.repo.productVariant.ProductVariantRepository;
import org.springframework.stereotype.Controller;

@Controller
public class ProductImageGraphQLMutation {

    private final ProductImageRepository productImageRepository;
    private final ProductImageMapper productImageMapper;
    private final ProductLogic productLogic;
    private final ProductVariantRepository productVariantRepository;

    public ProductImageGraphQLMutation(
            ProductImageRepository productImageRepository,
            ProductImageMapper productImageMapper,
            ProductLogic productLogic,
            ProductVariantRepository productVariantRepository
    ) {
        this.productImageRepository = productImageRepository;
        this.productImageMapper = productImageMapper;
        this.productLogic = productLogic;
        this.productVariantRepository = productVariantRepository;
    }

    @MutationMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    public ProductImageResponse addProductImage(
            @Argument Long productId,
            @Argument String imageToken
    ) {

        Product product = productLogic.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Ürün bulunamadı.")
                );

        User user = getCurrentUser();

        checkProductAccess(product, user);

        if (imageToken == null || imageToken.isBlank()) {
            throw new RuntimeException("Image token boş olamaz.");
        }

        ProductImage productImage = new ProductImage();
        productImage.setProduct(product);
        productImage.setImageToken(imageToken);

        ProductImage saved = productImageRepository.save(productImage);

        return productImageMapper.toResponse(saved);
    }
    @MutationMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    public ProductImageResponse addProductVariantImage(
            @Argument Long variantId,
            @Argument String imageToken
    ) {

        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() ->
                        new RuntimeException("Varyant bulunamadı.")
                );

        Product product = variant.getProduct();

        if (product == null) {
            throw new RuntimeException(
                    "Varyanta ait ürün bulunamadı."
            );
        }

        User user = getCurrentUser();

        checkProductAccess(product, user);

        if (imageToken == null || imageToken.isBlank()) {
            throw new RuntimeException(
                    "Image token boş olamaz."
            );
        }

        ProductImage productImage = new ProductImage();

        productImage.setProduct(product);
        productImage.setProductVariant(variant);
        productImage.setImageToken(imageToken);

        ProductImage saved =
                productImageRepository.save(productImage);

        return productImageMapper.toResponse(saved);
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

        if (user.getRole().getName() == RoleName.ADMIN) {
            return;
        }

        if (user.getRole().getName() != RoleName.STORE_MANAGER) {
            throw new RuntimeException("Bu işlem için yetkiniz yok.");
        }

        Store store = product.getStore();

        if (store == null ||
                store.getOwner() == null ||
                !store.getOwner().getId().equals(user.getId())) {

            throw new RuntimeException(
                    "Bu ürüne fotoğraf ekleme yetkiniz yok."
            );
        }
    }
}
