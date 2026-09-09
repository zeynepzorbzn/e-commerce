package com.zeynep.eTicaretSitesi.service.analytics;

import com.zeynep.eTicaretSitesi.core.entity.AnalyticsEvent;
import com.zeynep.eTicaretSitesi.core.entity.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class AnalyticsEventMutation {

    private final AnalyticsEventService analyticsEventService;

    public AnalyticsEventMutation(
            AnalyticsEventService analyticsEventService
    ) {
        this.analyticsEventService = analyticsEventService;
    }

    @MutationMapping
    public Boolean trackAnalyticsEvent(
            @Argument AnalyticsEventInput input
    ) {
        Long userId = getAuthenticatedUserId();

        AnalyticsEvent event = analyticsEventService.track(
                input.getEventType(),
                userId,
                input.getStoreId(),
                input.getProductId(),
                input.getCategoryId(),
                input.getBrandId(),
                input.getOrderId(),
                input.getSessionId(),
                input.getValue(),
                null
        );

        return event != null;
    }

    private Long getAuthenticatedUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof User user) {
            return user.getId();
        }

        return null;
    }
}