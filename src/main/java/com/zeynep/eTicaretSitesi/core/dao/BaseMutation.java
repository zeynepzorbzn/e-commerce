package com.zeynep.eTicaretSitesi.core.dao;

public abstract class BaseMutation<
        E extends BaseEntity, I extends BaseInput<E>, ID, L extends BaseLogic<E, ID, R>, M extends BaseMapper<E, I, RE>,
        R extends BaseRepository<E, ID>, RE extends BaseResponse<E>> {

    protected final BaseService<E, I, ID, L, M, R, RE> service;

    protected BaseMutation(BaseService<E, I, ID, L, M, R, RE> service) {
        this.service = service;
    }
}


