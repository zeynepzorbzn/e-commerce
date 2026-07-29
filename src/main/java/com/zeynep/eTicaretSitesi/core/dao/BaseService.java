package com.zeynep.eTicaretSitesi.core.dao;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<E extends BaseEntity, I extends BaseInput<E>, ID, L extends BaseLogic<E,ID, R>,
        M extends BaseMapper<E,I,RE>, R extends BaseRepository<E,ID>, RE extends BaseResponse<E>> {

    protected final R repository;
    protected final L logic;
    protected final M mapper;

    protected BaseService(R repository, L logic, M mapper) {
        this.repository = repository;
        this.logic = logic;
        this.mapper = mapper;
    }

    public E save(E entity) {
        return (E) repository.save(entity);
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

}