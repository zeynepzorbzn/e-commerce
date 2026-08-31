package com.zeynep.eTicaretSitesi.service.address;

import com.zeynep.eTicaretSitesi.dto.address.AddressResponse;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AddressQuery {

    private final AddressService addressService;

    public AddressQuery(AddressService addressService) {
        this.addressService = addressService;
    }

    @QueryMapping
    @PreAuthorize("hasRole('USER')")
    public List<AddressResponse> getMyAddresses() {
        return addressService.getMyAddresses();
    }
}