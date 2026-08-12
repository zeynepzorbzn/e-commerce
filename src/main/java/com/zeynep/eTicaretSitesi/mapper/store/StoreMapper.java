package com.zeynep.eTicaretSitesi.mapper.store;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.Store;
import com.zeynep.eTicaretSitesi.core.entity.embeddable.StoreAddress;
import com.zeynep.eTicaretSitesi.dto.store.StoreInput;
import com.zeynep.eTicaretSitesi.dto.store.StoreResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoreMapper extends BaseMapper <Store, StoreInput, StoreResponse>{

@Override
    public Store toEntity(StoreInput input){

    Store store = new Store();
    store.setName(input.getName());
    store.setEmail(input.getEmail());
    store.setPhoneNumber(input.getPhoneNumber());

    StoreAddress address = new StoreAddress();
    address.setCity(input.getCity());
    address.setDistrict(input.getDistrict());
    address.setStreet(input.getStreet());
    address.setPostalCode(input.getPostalCode());
    store.setAddress(address);
    return store;
}
@Override
    public StoreResponse toResponse(Store store){

    StoreResponse response = new StoreResponse();

    response.setId(store.getId());
    response.setName(store.getName());
    response.setEmail(store.getEmail());
    response.setPhoneNumber(store.getPhoneNumber());
    response.setOwnerName(store.getOwner().getFirstName() + " " + store.getOwner().getLastName());

    response.setCity(store.getAddress().getCity());
    response.setDistrict(store.getAddress().getDistrict());
    response.setStreet(store.getAddress().getStreet());
    response.setPostalCode(store.getAddress().getPostalCode());

    return response;
   }

    @Override
    public void updateEntity(Store store, StoreInput input) {
        store.setName(input.getName());
        store.setEmail(input.getEmail());
        store.setPhoneNumber(input.getPhoneNumber());

        StoreAddress address = store.getAddress();

        if (address == null) {address = new StoreAddress();}
        address.setCity(input.getCity());
        address.setDistrict(input.getDistrict());
        address.setStreet(input.getStreet());
        address.setPostalCode(input.getPostalCode());

        store.setAddress(address);
    }
    @Override
    public List<StoreResponse> toResponseList(List<Store> stores) {
        return stores.stream().map(this::toResponse).toList();
    }
}
