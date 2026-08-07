package com.zeynep.eTicaretSitesi.service.user;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.dto.user.input.UserInput;
import com.zeynep.eTicaretSitesi.dto.user.response.UserResponse;
import com.zeynep.eTicaretSitesi.logic.role.RoleLogic;
import com.zeynep.eTicaretSitesi.logic.user.UserLogic;
import com.zeynep.eTicaretSitesi.mapper.user.UserMapper;
import com.zeynep.eTicaretSitesi.repo.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserInput, Long, UserLogic, UserMapper, UserRepository, UserResponse> {

    private final PasswordEncoder passwordEncoder;
    private final RoleLogic roleLogic;

    public UserService(UserRepository repository, UserLogic logic, UserMapper mapper, PasswordEncoder passwordEncoder, RoleLogic roleLogic) {
        super(repository, logic, mapper);
        this.passwordEncoder = passwordEncoder;
        this.roleLogic = roleLogic;
    }

    @Override
    public UserResponse create(UserInput input) {

        User user = logic.createUser(input);
        user.setRole(roleLogic.findByName(input.getRole()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = repository.save(user);

        return logic.toResponse(savedUser);
    }
}