package com.zeynep.eTicaretSitesi.logic.user;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.core.entity.Role;
import com.zeynep.eTicaretSitesi.dto.user.input.UserInput;
import com.zeynep.eTicaretSitesi.dto.user.response.UserResponse;
import com.zeynep.eTicaretSitesi.mapper.user.UserMapper;
import com.zeynep.eTicaretSitesi.repo.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserLogic extends BaseLogic<User, Long, UserRepository>{

    private final UserMapper mapper;

    protected UserLogic(UserRepository repository, UserMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }
    public User createUser(UserInput input) {
        User user = mapper.toEntity(input);
        //user.setRole(Role.USER);
        return user;
    }
    public UserResponse toResponse(User user) {
        return mapper.toResponse(user);
    }
}
