package com.zeynep.eTicaretSitesi.service.store;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.Store;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.core.enums.RoleName;
import com.zeynep.eTicaretSitesi.dto.store.StoreInput;
import com.zeynep.eTicaretSitesi.dto.store.StoreResponse;
import com.zeynep.eTicaretSitesi.logic.store.StoreLogic;
import com.zeynep.eTicaretSitesi.logic.user.UserLogic;
import com.zeynep.eTicaretSitesi.mapper.store.StoreMapper;
import com.zeynep.eTicaretSitesi.repo.store.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreService extends BaseService<Store, StoreInput, Long, StoreLogic, StoreMapper, StoreRepository, StoreResponse> {

    private final UserLogic userLogic;

    public StoreService(StoreRepository repository, StoreLogic logic, StoreMapper mapper, UserLogic userLogic) {

        super(repository, logic, mapper);
        this.userLogic = userLogic;
    }

    @Override
    public StoreResponse create(StoreInput input) {

        Store store = logic.createStore(input);
        User owner = userLogic.findById(input.getOwnerId())
                .orElseThrow(() ->
                        new RuntimeException("Mağaza sahibi bulunamadı."));

        if (owner.getRole() == null ||
                owner.getRole().getName() != RoleName.STORE_MANAGER) {

            throw new RuntimeException(
                    "Mağaza sahibi STORE_MANAGER rolüne sahip olmalıdır."
            );
        }

        store.setOwner(owner);
        Store savedStore = repository.save(store);

        return logic.toResponse(savedStore);
    }
}