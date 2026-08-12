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
    public List<E> findAll() {
        return repository.findAll();
    }

    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }

    public RE create(I input) {
        E entity = mapper.toEntity(input);
        E savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }
    public List<RE> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    public RE getById(ID id) {
        E entity = logic.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        return mapper.toResponse(entity);
    }

    public RE update(ID id, I input){
        E entity  =repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        mapper.updateEntity(entity, input);
        E updated =  repository.save(entity);
        return mapper.toResponse(updated);
    }

    public void delete(ID id) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        repository.deleteById(id);
    }
}
