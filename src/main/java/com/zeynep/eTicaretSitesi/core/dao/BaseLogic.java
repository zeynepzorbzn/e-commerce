package com.zeynep.eTicaretSitesi.core.dao;

import org.springframework.stereotype.Repository;
import tools.jackson.core.ObjectReadContext;

public abstract class BaseLogic<E extends BaseEntity,ID, R extends BaseRepository<E,ID>> {

    protected final R repository;

    protected BaseLogic(R repository){
        this.repository = repository;
    }

}
