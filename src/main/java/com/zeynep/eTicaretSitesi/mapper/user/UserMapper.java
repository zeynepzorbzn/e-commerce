package com.zeynep.eTicaretSitesi.mapper.user;
import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.dto.user.input.UserInput;
import com.zeynep.eTicaretSitesi.dto.user.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<User, UserInput, UserResponse> {
}
