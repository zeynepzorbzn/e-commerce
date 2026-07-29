package com.zeynep.eTicaretSitesi.logic.user;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.dao.BaseRepository;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.repo.user.UserRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;

@Service
public class UserLogic extends BaseLogic<User, Long, UserRepository>{

    protected UserLogic(UserRepository repository) {

        super( repository);
    }
}
