package com.zeynep.eTicaretSitesi.mapper.address;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.Address;
import com.zeynep.eTicaretSitesi.dto.address.AddressInput;
import com.zeynep.eTicaretSitesi.dto.address.AddressResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressMapper
        extends BaseMapper<Address, AddressInput, AddressResponse> {

    @Override
    public Address toEntity(AddressInput input) {

        Address address = new Address();

        address.setName(input.getName());
        address.setCity(input.getCity());
        address.setDistrict(input.getDistrict());
        address.setStreet(input.getStreet());
        address.setPostalCode(input.getPostalCode());
        address.setBilling(input.isBilling());

        return address;
    }

    @Override
    public AddressResponse toResponse(Address address) {

        AddressResponse response = new AddressResponse();

        response.setId(address.getId());
        response.setName(address.getName());
        response.setCity(address.getCity());
        response.setDistrict(address.getDistrict());
        response.setStreet(address.getStreet());
        response.setPostalCode(address.getPostalCode());
        response.setBilling(address.getBilling());

        return response;
    }

    @Override
    public void updateEntity(Address address, AddressInput input) {

        address.setName(input.getName());
        address.setCity(input.getCity());
        address.setDistrict(input.getDistrict());
        address.setStreet(input.getStreet());
        address.setPostalCode(input.getPostalCode());
        address.setBilling(input.isBilling());
    }

    @Override
    public List<AddressResponse> toResponseList(List<Address> addresses) {
        return addresses.stream().map(this::toResponse).toList();
    }
}