package com.zeynep.eTicaretSitesi.mapper.user;
import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.dto.user.input.UserInput;
import com.zeynep.eTicaretSitesi.dto.user.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper extends BaseMapper<User, UserInput, UserResponse> {

    public User toEntity(UserInput input) {
        User user = new User();
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(input.getPassword());
        user.setBirthDate(input.getBirthDate());
        user.setPhoneNumber(input.getPhoneNumber());
        return user;
    }
    public UserResponse toResponse(User savedUser) {

        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setEmail(savedUser.getEmail());
        response.setBirthDate(savedUser.getBirthDate());
        response.setPhoneNumber(savedUser.getPhoneNumber());
        return response;
    }
    @Override
    public List<UserResponse> toResponseList(List<User> entities) {
        return entities.stream().map(this::toResponse).toList();
    }

    public void updateEntity(User user, UserInput input) {
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(input.getPassword());
        user.setBirthDate(input.getBirthDate());
    }

}

