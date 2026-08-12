package com.zeynep.eTicaretSitesi.dto.category;

import com.zeynep.eTicaretSitesi.core.dao.BaseResponse;
import com.zeynep.eTicaretSitesi.core.entity.Category;

public class CategoryResponse extends BaseResponse<Category> {

    private String name;
    private Long id;

    public CategoryResponse(){}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
}
