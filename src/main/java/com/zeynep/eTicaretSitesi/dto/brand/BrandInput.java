package com.zeynep.eTicaretSitesi.dto.brand;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.Brand;

public class BrandInput extends BaseInput<Brand> {

    private String name;

    public BrandInput() {
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

}