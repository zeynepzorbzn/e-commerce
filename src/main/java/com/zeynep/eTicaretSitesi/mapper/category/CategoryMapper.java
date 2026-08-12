package com.zeynep.eTicaretSitesi.mapper.category;

import com.zeynep.eTicaretSitesi.core.dao.BaseMapper;
import com.zeynep.eTicaretSitesi.core.entity.Category;
import com.zeynep.eTicaretSitesi.dto.category.CategoryInput;
import com.zeynep.eTicaretSitesi.dto.category.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper extends BaseMapper<Category, CategoryInput, CategoryResponse> {

    @Override
    public Category toEntity(CategoryInput input){

        Category category = new Category();
        category.setName(input.getName());
        return category;
    }
    @Override
    public CategoryResponse toResponse(Category category) {

        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());

        return response;
    }
    @Override
    public void updateEntity(Category entity, CategoryInput input) {
        entity.setName(input.getName());
    }
    @Override
    public List<CategoryResponse> toResponseList(List<Category> category) {
        return category.stream().map(this::toResponse).toList();
    }
}
