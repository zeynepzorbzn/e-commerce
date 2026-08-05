package com.zeynep.eTicaretSitesi.core.dao;

import java.util.List;


public abstract class BaseMapper <E extends BaseEntity,  I extends BaseInput<E>,  RE extends BaseResponse<E>> {

    public abstract void updateEntity(E entity, I input);
    public abstract E toEntity(I input);
    public abstract RE toResponse(E entity);
    public abstract List<RE> toResponseList(List<E> entities);




}
