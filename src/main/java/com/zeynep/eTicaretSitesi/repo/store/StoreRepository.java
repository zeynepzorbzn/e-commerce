package com.zeynep.eTicaretSitesi.repo.store;

import com.zeynep.eTicaretSitesi.core.dao.BaseRepository;
import com.zeynep.eTicaretSitesi.core.entity.Store;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends BaseRepository<Store, Long> {

    Optional<Store> findByOwnerId(Long ownerId);
}
