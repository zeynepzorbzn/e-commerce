package com.zeynep.eTicaretSitesi.repo.productVariant;

import com.zeynep.eTicaretSitesi.core.dao.BaseRepository;
import com.zeynep.eTicaretSitesi.core.entity.ProductVariant;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends BaseRepository<ProductVariant, Long> {
}
