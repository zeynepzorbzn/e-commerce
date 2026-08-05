package com.zeynep.eTicaretSitesi.service.user;

import com.zeynep.eTicaretSitesi.core.entity.Role;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.core.enums.RoleName;
import com.zeynep.eTicaretSitesi.dto.user.input.UserInput;
import com.zeynep.eTicaretSitesi.dto.user.response.UserResponse;
import com.zeynep.eTicaretSitesi.logic.user.UserLogic;
import com.zeynep.eTicaretSitesi.mapper.user.UserMapper;
import com.zeynep.eTicaretSitesi.repo.role.RoleRepository;
import com.zeynep.eTicaretSitesi.repo.user.UserRepository;
import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserInput, Long, UserLogic, UserMapper, UserRepository, UserResponse> {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository repository, UserLogic logic, UserMapper mapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        super(repository, logic, mapper);
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponse createUser(UserInput input) {
        User user = logic.createUser(input);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("USER rolü bulunamadı"));
        user.setRole(userRole);
        User savedUser = repository.save(user);
        return logic.toResponse(savedUser);}
    }


