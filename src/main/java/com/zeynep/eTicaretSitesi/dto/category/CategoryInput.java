package com.zeynep.eTicaretSitesi.dto.category;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.Category;


public class CategoryInput extends BaseInput<Category> {

    private String name;
    private String products;

    public CategoryInput(){}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getProducts() {return products;}
    public void setProducts(String products) {this.products = products;}
}
