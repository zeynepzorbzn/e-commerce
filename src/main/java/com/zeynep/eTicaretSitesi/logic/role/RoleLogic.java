package com.zeynep.eTicaretSitesi.logic.role;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.Role;
import com.zeynep.eTicaretSitesi.core.enums.RoleName;
import com.zeynep.eTicaretSitesi.core.exceptions.RoleNotFoundException;
import com.zeynep.eTicaretSitesi.repo.role.RoleRepository;
import org.springframework.stereotype.Component;


@Component
public class RoleLogic extends BaseLogic<Role, Long, RoleRepository> {

    public  RoleLogic(RoleRepository roleRepository){
        super(roleRepository);
    }

    public Role findByName(RoleName roleName){
        return repository.findByName(roleName).orElseThrow(() -> new RoleNotFoundException("Rol Bulunamadı"));
    }
}
