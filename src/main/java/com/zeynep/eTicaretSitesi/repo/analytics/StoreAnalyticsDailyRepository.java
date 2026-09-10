package com.zeynep.eTicaretSitesi.repo.analytics;

import com.zeynep.eTicaretSitesi.core.entity.StoreAnalyticsDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StoreAnalyticsDailyRepository
        extends JpaRepository<StoreAnalyticsDaily, Long> {

    List<StoreAnalyticsDaily> findByDateOrderByTotalRevenueDesc(
            LocalDate date
    );

    List<StoreAnalyticsDaily> findByStoreIdAndDate(
            Long storeId,
            LocalDate date
    );
}