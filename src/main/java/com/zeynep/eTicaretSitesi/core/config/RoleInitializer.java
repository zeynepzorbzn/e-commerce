package com.zeynep.eTicaretSitesi.core.config;

import com.zeynep.eTicaretSitesi.core.entity.Role;
import com.zeynep.eTicaretSitesi.core.enums.RoleName;
import com.zeynep.eTicaretSitesi.repo.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        createRoleIfNotExists(RoleName.USER);
        createRoleIfNotExists(RoleName.ADMIN);
        createRoleIfNotExists(RoleName.STORE_MANAGER);
    }

    private void createRoleIfNotExists(RoleName roleName) {
        if (roleRepository.findByName(roleName).isEmpty()) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }
}