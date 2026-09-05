package com.zeynep.eTicaretSitesi.logic.store;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.Store;
import com.zeynep.eTicaretSitesi.dto.store.StoreInput;
import com.zeynep.eTicaretSitesi.dto.store.StoreResponse;
import com.zeynep.eTicaretSitesi.mapper.store.StoreMapper;
import com.zeynep.eTicaretSitesi.repo.store.StoreRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StoreLogic extends BaseLogic<Store, Long, StoreRepository> {

    private final StoreMapper mapper;

    public StoreLogic(StoreRepository repository, StoreMapper mapper){
        super(repository);
        this.mapper = mapper;
    }

    public Store createStore(StoreInput input){
        return mapper.toEntity(input);
    }

    public StoreResponse toResponse(Store store){
        return mapper.toResponse(store);
    }

    public Optional<Store> findByOwnerId(Long ownerId) {
        return repository.findByOwnerId(ownerId);
    }
}