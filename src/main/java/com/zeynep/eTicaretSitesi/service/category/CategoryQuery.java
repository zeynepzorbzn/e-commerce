package com.zeynep.eTicaretSitesi.service.category;

import com.zeynep.eTicaretSitesi.dto.category.CategoryResponse;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryQuery {

    private final CategoryService categoryService;

    public CategoryQuery(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @QueryMapping
    public List<CategoryResponse> getCategories() {
        return categoryService.getAll();
    }
}