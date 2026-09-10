package com.zeynep.eTicaretSitesi.service.analytics;

import com.zeynep.eTicaretSitesi.core.entity.PlatformAnalyticsDaily;
import com.zeynep.eTicaretSitesi.core.entity.ProductAnalyticsDaily;
import com.zeynep.eTicaretSitesi.core.entity.StoreAnalyticsDaily;
import com.zeynep.eTicaretSitesi.dto.analytics.PlatformAnalyticsResponse;
import com.zeynep.eTicaretSitesi.dto.analytics.ProductAnalyticsResponse;
import com.zeynep.eTicaretSitesi.dto.analytics.StoreAnalyticsResponse;
import com.zeynep.eTicaretSitesi.repo.analytics.PlatformAnalyticsDailyRepository;
import com.zeynep.eTicaretSitesi.repo.analytics.ProductAnalyticsDailyRepository;
import com.zeynep.eTicaretSitesi.repo.analytics.StoreAnalyticsDailyRepository;
import com.zeynep.eTicaretSitesi.repo.store.StoreRepository;
import com.zeynep.eTicaretSitesi.core.entity.Store;
import com.zeynep.eTicaretSitesi.core.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnalyticsQueryService {

    private final ProductAnalyticsDailyRepository productRepository;
    private final StoreAnalyticsDailyRepository storeRepository;
    private final PlatformAnalyticsDailyRepository platformRepository;
    private final StoreRepository storeOwnerRepository;

    public AnalyticsQueryService(
            ProductAnalyticsDailyRepository productRepository,
            StoreAnalyticsDailyRepository storeRepository,
            PlatformAnalyticsDailyRepository platformRepository,
            StoreRepository storeOwnerRepository
    ) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.platformRepository = platformRepository;
        this.storeOwnerRepository = storeOwnerRepository;
    }

    public PlatformAnalyticsResponse getPlatformAnalytics(
            LocalDate date
    ) {
        PlatformAnalyticsDaily analytics =
                platformRepository.findByDate(date)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Bu tarih için platform analytics bulunamadı."
                                )
                        );

        return toPlatformResponse(analytics);
    }

    public List<StoreAnalyticsResponse> getStoreAnalytics(
            LocalDate date
    ) {
        return storeRepository
                .findByDateOrderByTotalRevenueDesc(date)
                .stream()
                .map(this::toStoreResponse)
                .toList();
    }

    public StoreAnalyticsResponse getMyStoreAnalytics(
            LocalDate date
    ) {
        Store store = getAuthenticatedStore();

        return storeRepository
                .findByStoreIdAndDate(
                        store.getId(),
                        date
                )
                .stream()
                .findFirst()
                .map(this::toStoreResponse)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Bu tarih için mağaza analytics bulunamadı."
                        )
                );
    }

    public List<ProductAnalyticsResponse> getMyProductAnalytics(
            LocalDate date
    ) {
        Store store = getAuthenticatedStore();

        return productRepository
                .findByStoreIdAndDateOrderByViewCountDesc(
                        store.getId(),
                        date
                )
                .stream()
                .map(this::toProductResponse)
                .toList();
    }

    public List<ProductAnalyticsResponse> getTopViewedProducts(
            LocalDate date
    ) {
        return productRepository
                .findTop10ByDateOrderByViewCountDesc(date)
                .stream()
                .map(this::toProductResponse)
                .toList();
    }

    public List<ProductAnalyticsResponse> getTopSellingProducts(
            LocalDate date
    ) {
        return productRepository
                .findTop10ByDateOrderByPurchaseCountDesc(date)
                .stream()
                .map(this::toProductResponse)
                .toList();
    }

    private Store getAuthenticatedStore() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated()) {

            throw new RuntimeException(
                    "Kimlik doğrulaması gerekli."
            );
        }

        Object principal =
                authentication.getPrincipal();

        if (!(principal instanceof User user)) {
            throw new RuntimeException(
                    "Geçersiz kullanıcı."
            );
        }

        return storeOwnerRepository
                .findByOwnerId(user.getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Kullanıcıya ait mağaza bulunamadı."
                        )
                );
    }

    private ProductAnalyticsResponse toProductResponse(
            ProductAnalyticsDaily analytics
    ) {
        return new ProductAnalyticsResponse(
                analytics.getProductId(),
                analytics.getStoreId(),
                analytics.getDate(),
                analytics.getViewCount(),
                analytics.getCartAddCount(),
                analytics.getPurchaseCount(),
                analytics.getRevenue(),
                analytics.getConversionRate(),
                analytics.getAvgPrice(),
                analytics.getStockImpactScore()
        );
    }

    private StoreAnalyticsResponse toStoreResponse(
            StoreAnalyticsDaily analytics
    ) {
        return new StoreAnalyticsResponse(
                analytics.getStoreId(),
                analytics.getDate(),
                analytics.getTotalViews(),
                analytics.getTotalProductsSold(),
                analytics.getTotalRevenue(),
                analytics.getTotalOrders(),
                analytics.getAvgOrderValue(),
                analytics.getReturningCustomerRate(),
                analytics.getConversionRate()
        );
    }

    private PlatformAnalyticsResponse toPlatformResponse(
            PlatformAnalyticsDaily analytics
    ) {
        return new PlatformAnalyticsResponse(
                analytics.getDate(),
                analytics.getTotalUsers(),
                analytics.getActiveUsers(),
                analytics.getNewUsers(),
                analytics.getTotalRevenue(),
                analytics.getTotalOrders(),
                analytics.getTotalStores(),
                analytics.getTopSellingProductId(),
                analytics.getTopStoreId(),
                analytics.getSystemConversionRate()
        );
    }
}