package com.zeynep.eTicaretSitesi.service.user.query;

import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.core.dao.BaseQuery;
import com.zeynep.eTicaretSitesi.dto.user.input.UserInput;
import com.zeynep.eTicaretSitesi.dto.user.response.UserResponse;
import com.zeynep.eTicaretSitesi.logic.user.UserLogic;
import com.zeynep.eTicaretSitesi.mapper.user.UserMapper;
import com.zeynep.eTicaretSitesi.repo.user.UserRepository;
import com.zeynep.eTicaretSitesi.service.user.UserService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserQuery extends BaseQuery<User, UserInput, Long, UserLogic, UserMapper, UserRepository, UserResponse> {

    public UserQuery(UserService userService) {
        super(userService);
    }

    @QueryMapping
    public List<User> users() {
        return service.findAll();
    }
}


