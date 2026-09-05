package com.zeynep.eTicaretSitesi.repo.product;

import com.zeynep.eTicaretSitesi.core.dao.BaseRepository;
import com.zeynep.eTicaretSitesi.core.entity.Product;
import com.zeynep.eTicaretSitesi.core.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
    List<Product> findByCategory_Id(Long categoryId);
    List<Product> findByStore_Id(Long storeId);
    List<Product> findByStoreId(Long storeId);

}