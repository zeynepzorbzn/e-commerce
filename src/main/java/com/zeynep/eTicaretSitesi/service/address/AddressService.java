package com.zeynep.eTicaretSitesi.service.address;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.Address;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.dto.address.AddressInput;
import com.zeynep.eTicaretSitesi.dto.address.AddressResponse;
import com.zeynep.eTicaretSitesi.logic.address.AddressLogic;
import com.zeynep.eTicaretSitesi.mapper.address.AddressMapper;
import com.zeynep.eTicaretSitesi.repo.address.AddressRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService extends BaseService<Address, AddressInput, Long, AddressLogic, AddressMapper, AddressRepository, AddressResponse> {

    public AddressService(AddressRepository repository, AddressLogic logic, AddressMapper mapper) {
        super(repository, logic, mapper);
    }

    @Override
    public AddressResponse create(AddressInput input) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Address address = mapper.toEntity(input);
        address.setUser(user);

        Address savedAddress = repository.save(address);

        return mapper.toResponse(savedAddress);
    }

    public List<AddressResponse> getMyAddresses() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        return mapper.toResponseList(
                repository.findByUserId(user.getId())
        );
    }
}