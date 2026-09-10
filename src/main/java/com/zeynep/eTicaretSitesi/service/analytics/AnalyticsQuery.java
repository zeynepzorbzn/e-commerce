package com.zeynep.eTicaretSitesi.service.analytics;

import com.zeynep.eTicaretSitesi.dto.analytics.PlatformAnalyticsResponse;
import com.zeynep.eTicaretSitesi.dto.analytics.ProductAnalyticsResponse;
import com.zeynep.eTicaretSitesi.dto.analytics.StoreAnalyticsResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AnalyticsQuery {

    private final AnalyticsQueryService analyticsQueryService;

    public AnalyticsQuery(
            AnalyticsQueryService analyticsQueryService
    ) {
        this.analyticsQueryService =
                analyticsQueryService;
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PlatformAnalyticsResponse getPlatformAnalytics(
            @Argument LocalDate date
    ) {
        return analyticsQueryService
                .getPlatformAnalytics(date);
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<StoreAnalyticsResponse> getStoreAnalytics(
            @Argument LocalDate date
    ) {
        return analyticsQueryService
                .getStoreAnalytics(date);
    }

    @QueryMapping
    @PreAuthorize("hasRole('STORE_MANAGER')")
    public StoreAnalyticsResponse getMyStoreAnalytics(
            @Argument LocalDate date
    ) {
        return analyticsQueryService
                .getMyStoreAnalytics(date);
    }

    @QueryMapping
    @PreAuthorize("hasRole('STORE_MANAGER')")
    public List<ProductAnalyticsResponse> getMyProductAnalytics(
            @Argument LocalDate date
    ) {
        return analyticsQueryService
                .getMyProductAnalytics(date);
    }

    @QueryMapping
    public List<ProductAnalyticsResponse> getTopViewedProducts(
            @Argument LocalDate date
    ) {
        return analyticsQueryService
                .getTopViewedProducts(date);
    }

    @QueryMapping
    public List<ProductAnalyticsResponse> getTopSellingProducts(
            @Argument LocalDate date
    ) {
        return analyticsQueryService
                .getTopSellingProducts(date);
    }
}