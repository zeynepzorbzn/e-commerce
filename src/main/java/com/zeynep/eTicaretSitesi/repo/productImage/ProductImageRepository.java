package com.zeynep.eTicaretSitesi.repo.productImage;

import com.zeynep.eTicaretSitesi.core.dao.BaseRepository;
import com.zeynep.eTicaretSitesi.core.entity.ProductImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends BaseRepository<ProductImage, Long> {

    List<ProductImage> findByProduct_Id(Long productId);
}