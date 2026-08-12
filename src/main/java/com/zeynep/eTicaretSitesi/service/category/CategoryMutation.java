package com.zeynep.eTicaretSitesi.service.category;


import com.zeynep.eTicaretSitesi.dto.category.CategoryInput;
import com.zeynep.eTicaretSitesi.dto.category.CategoryResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryMutation {

    private final CategoryService categoryService;

    public CategoryMutation(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse createCategory(@Argument CategoryInput input) {
        return categoryService.create(input);
    }
}