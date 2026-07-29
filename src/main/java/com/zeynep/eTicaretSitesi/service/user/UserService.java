package com.zeynep.eTicaretSitesi.service.user;

import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.dto.user.input.UserInput;
import com.zeynep.eTicaretSitesi.dto.user.response.UserResponse;
import com.zeynep.eTicaretSitesi.logic.user.UserLogic;
import com.zeynep.eTicaretSitesi.mapper.user.UserMapper;
import com.zeynep.eTicaretSitesi.repo.user.UserRepository;
import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User, UserInput, Long, UserLogic, UserMapper, UserRepository, UserResponse> {

    public UserService(UserRepository repository,
                        UserLogic logic,
                        UserMapper mapper) {

        super(repository, logic, mapper);
    }
}

