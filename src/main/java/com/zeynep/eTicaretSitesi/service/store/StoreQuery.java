package com.zeynep.eTicaretSitesi.service.store;

import com.zeynep.eTicaretSitesi.dto.store.StoreResponse;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StoreQuery {

    private final StoreService storeService;

    public StoreQuery(StoreService storeService) {
        this.storeService = storeService;
    }

    @QueryMapping
    public List<StoreResponse> getStores() {
        return storeService.getAll();
    }

    @QueryMapping
    @PreAuthorize("hasRole('STORE_MANAGER')")
    public StoreResponse getMyStore(){
        return storeService.getMyStore();
    }
}