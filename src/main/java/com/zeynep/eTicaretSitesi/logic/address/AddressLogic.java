package com.zeynep.eTicaretSitesi.logic.address;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.Address;
import com.zeynep.eTicaretSitesi.dto.address.AddressInput;
import com.zeynep.eTicaretSitesi.dto.address.AddressResponse;
import com.zeynep.eTicaretSitesi.mapper.address.AddressMapper;
import com.zeynep.eTicaretSitesi.repo.address.AddressRepository;
import org.springframework.stereotype.Component;

@Component
public class AddressLogic extends BaseLogic<Address, Long, AddressRepository> {

    private final AddressMapper mapper;

    public AddressLogic(AddressRepository repository, AddressMapper mapper){
        super(repository);
        this.mapper=mapper;
    }
    public Address createAddress(AddressInput input){
        return mapper.toEntity(input);
    }
    public AddressResponse toResponse(Address address){
        return mapper.toResponse(address);
    }


}