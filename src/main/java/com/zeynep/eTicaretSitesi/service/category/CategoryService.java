package com.zeynep.eTicaretSitesi.service.category;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.Category;
import com.zeynep.eTicaretSitesi.dto.category.CategoryInput;
import com.zeynep.eTicaretSitesi.dto.category.CategoryResponse;
import com.zeynep.eTicaretSitesi.logic.category.CategoryLogic;
import com.zeynep.eTicaretSitesi.mapper.category.CategoryMapper;
import com.zeynep.eTicaretSitesi.repo.category.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, CategoryInput, Long, CategoryLogic, CategoryMapper, CategoryRepository, CategoryResponse>
{
    public CategoryService(CategoryRepository repository, CategoryLogic logic, CategoryMapper mapper ){

        super(repository, logic, mapper);
    }
    @Override
    public CategoryResponse create(CategoryInput input) {

        Category category = logic.createCategory(input);
        Category savedCategory = repository.save(category);

        return logic.toResponse(savedCategory);
    }


}
