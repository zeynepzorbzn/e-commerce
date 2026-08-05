package com.zeynep.eTicaretSitesi.service.user.mutation;

import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.core.dao.BaseMutation;
import com.zeynep.eTicaretSitesi.dto.user.input.UserInput;
import com.zeynep.eTicaretSitesi.dto.user.response.UserResponse;
import com.zeynep.eTicaretSitesi.logic.user.UserLogic;
import com.zeynep.eTicaretSitesi.mapper.user.UserMapper;
import com.zeynep.eTicaretSitesi.repo.user.UserRepository;
import com.zeynep.eTicaretSitesi.service.user.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;


@Controller
public class UserMutation extends BaseMutation<User, UserInput, Long, UserLogic, UserMapper, UserRepository, UserResponse> {

    public UserMutation(UserService userService) {

        super(userService);
    }

    @MutationMapping
    public UserResponse createUser(@Argument UserInput input){
        return service.createUser(input);

    }
    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String adminTest() {
        return "Merhaba Admin!";
    }

}