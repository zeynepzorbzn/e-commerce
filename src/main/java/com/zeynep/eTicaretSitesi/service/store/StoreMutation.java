package com.zeynep.eTicaretSitesi.service.store;


import com.zeynep.eTicaretSitesi.dto.store.StoreInput;
import com.zeynep.eTicaretSitesi.dto.store.StoreResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class StoreMutation {

    private final StoreService storeService;
    public StoreMutation(StoreService storeService) {
        this.storeService = storeService;
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public StoreResponse createStore(@Argument StoreInput input) {
        return storeService.create(input);
    }
}