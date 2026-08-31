package com.zeynep.eTicaretSitesi.repo.address;

import com.zeynep.eTicaretSitesi.core.dao.BaseRepository;
import com.zeynep.eTicaretSitesi.core.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long> {

    List<Address> findByUserId(Long userId);

    Optional<Address> findByIdAndUserId(Long id, Long userId);
}