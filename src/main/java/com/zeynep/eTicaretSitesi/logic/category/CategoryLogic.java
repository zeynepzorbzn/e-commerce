package com.zeynep.eTicaretSitesi.logic.category;

import com.zeynep.eTicaretSitesi.core.dao.BaseLogic;
import com.zeynep.eTicaretSitesi.core.entity.Category;
import com.zeynep.eTicaretSitesi.dto.category.CategoryInput;
import com.zeynep.eTicaretSitesi.dto.category.CategoryResponse;
import com.zeynep.eTicaretSitesi.mapper.category.CategoryMapper;

import com.zeynep.eTicaretSitesi.repo.category.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryLogic extends BaseLogic<Category, Long, CategoryRepository> {

    private final CategoryMapper mapper;

    public CategoryLogic(CategoryRepository repository, CategoryMapper mapper){
        super(repository);
        this.mapper=mapper;
    }
    public Category createCategory(CategoryInput input){
        return mapper.toEntity(input);
    }
    public CategoryResponse toResponse(Category category){
        return mapper.toResponse(category);
    }

}