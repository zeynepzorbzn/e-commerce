package com.zeynep.eTicaretSitesi.mapper.product;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.Product;
import com.zeynep.eTicaretSitesi.dto.product.input.ProductInput;
import com.zeynep.eTicaretSitesi.dto.product.response.ProductResponse;
import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantResponse;
import com.zeynep.eTicaretSitesi.dto.productImage.ProductImageResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper
        extends BaseMapper<Product, ProductInput, ProductResponse> {

    @Override
    public Product toEntity(ProductInput input) {

        Product product = new Product();

        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setGender(input.getGender());
        product.setSeason(input.getSeason());

        return product;
    }

    @Override
    public ProductResponse toResponse(Product product) {

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setGender(product.getGender());
        response.setSeason(product.getSeason());
        response.setStoreName(product.getStore().getName());
        response.setBrandName(product.getBrand().getName());
        response.setCategoryName(product.getCategory().getName());

        response.setVariants(
                product.getVariants() == null
                        ? List.of()
                        : product.getVariants().stream().map(variant -> {

                    ProductVariantResponse variantResponse =
                            new ProductVariantResponse();

                    variantResponse.setId(variant.getId());
                    variantResponse.setSize(variant.getSize());
                    variantResponse.setColor(variant.getColor());
                    variantResponse.setStock(variant.getStock());
                    variantResponse.setProductId(product.getId());
                    variantResponse.setProductName(product.getName());
                    variantResponse.setImages(
                    variant.getImages() == null ? List.of() : variant.getImages().stream().map(image -> {
                     ProductImageResponse imageResponse = new ProductImageResponse();

                     imageResponse.setId(image.getId());
                     imageResponse.setImageToken(image.getImageToken());

                     if (image.getProduct() != null) {
                         imageResponse.setProductId(image.getProduct().getId());
                     }
                     if (image.getProductVariant() != null) {imageResponse.setVariantId(image.getProductVariant().getId());
                     }
                    return imageResponse;
                    }).toList());

                    return variantResponse;
                    }).toList());

        response.setImages(
                product.getProductImages() == null
                        ? List.of()
                        : product.getProductImages()
                        .stream()
                        .filter(image -> image.getProductVariant() == null)
                        .map(image -> {

                            ProductImageResponse imageResponse =
                                    new ProductImageResponse();

                            imageResponse.setId(image.getId());
                            imageResponse.setImageToken(
                                    image.getImageToken()
                            );

                            if (image.getProduct() != null) {
                                imageResponse.setProductId(
                                        image.getProduct().getId()
                                );
                            }

                            return imageResponse;

                        })
                        .toList()
        );
        return response;
    }

    @Override
    public void updateEntity(Product entity, ProductInput input) {
        entity.setName(input.getName());
    }

    @Override
    public List<ProductResponse> toResponseList(List<Product> products) {
        return products.stream()
                .map(this::toResponse)
                .toList();
    }
}
