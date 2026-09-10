package com.zeynep.eTicaretSitesi.repo.analytics;

import com.zeynep.eTicaretSitesi.core.entity.ProductAnalyticsDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductAnalyticsDailyRepository
        extends JpaRepository<ProductAnalyticsDaily, Long> {

    List<ProductAnalyticsDaily> findByDateOrderByViewCountDesc(
            LocalDate date
    );

    List<ProductAnalyticsDaily> findByStoreIdAndDateOrderByViewCountDesc(
            Long storeId,
            LocalDate date
    );

    List<ProductAnalyticsDaily> findTop10ByDateOrderByViewCountDesc(
            LocalDate date
    );

    List<ProductAnalyticsDaily> findTop10ByDateOrderByPurchaseCountDesc(
            LocalDate date
    );
}