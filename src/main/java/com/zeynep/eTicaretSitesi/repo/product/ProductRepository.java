package com.zeynep.eTicaretSitesi.repo.product;

import com.zeynep.eTicaretSitesi.core.dao.BaseRepository;
import com.zeynep.eTicaretSitesi.core.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    List<Product> findByCategory_Id(Long categoryId);

    List<Product> findByStore_Id(Long storeId);

    List<Product> findByStoreId(Long storeId);

    @Query("""
            SELECT p
            FROM Product p
            JOIN p.brand b
            JOIN p.category c
            JOIN p.store s
            WHERE (
                LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(COALESCE(p.description, '')) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(b.name) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%'))
            )
            AND (:categoryId IS NULL OR c.id = :categoryId)
            ORDER BY p.createdAt DESC
            """)
    List<Product> searchProducts(
            @Param("query") String query,
            @Param("categoryId") Long categoryId
    );
}