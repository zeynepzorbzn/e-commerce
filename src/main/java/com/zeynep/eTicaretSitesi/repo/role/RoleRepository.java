package com.zeynep.eTicaretSitesi.repo.role;

import com.zeynep.eTicaretSitesi.core.dao.BaseRepository;
import com.zeynep.eTicaretSitesi.core.entity.Role;
import com.zeynep.eTicaretSitesi.core.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tools.jackson.core.ObjectReadContext;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);

}
