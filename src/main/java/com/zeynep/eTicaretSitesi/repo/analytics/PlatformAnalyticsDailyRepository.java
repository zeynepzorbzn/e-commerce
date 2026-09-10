package com.zeynep.eTicaretSitesi.repo.analytics;

import com.zeynep.eTicaretSitesi.core.entity.PlatformAnalyticsDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PlatformAnalyticsDailyRepository
        extends JpaRepository<PlatformAnalyticsDaily, Long> {

    Optional<PlatformAnalyticsDaily> findByDate(
            LocalDate date
    );
}