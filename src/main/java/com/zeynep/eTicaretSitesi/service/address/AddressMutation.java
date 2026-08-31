package com.zeynep.eTicaretSitesi.service.address;

import com.zeynep.eTicaretSitesi.dto.address.AddressInput;
import com.zeynep.eTicaretSitesi.dto.address.AddressResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class AddressMutation {

    private final AddressService addressService;

    public AddressMutation(AddressService addressService) {
        this.addressService = addressService;
    }

    @MutationMapping
    @PreAuthorize("hasRole('USER')")
    public AddressResponse createAddress(
            @Argument AddressInput input
    ) {
        return addressService.create(input);
    }
}