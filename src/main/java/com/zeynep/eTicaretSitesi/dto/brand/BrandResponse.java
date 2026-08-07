package com.zeynep.eTicaretSitesi.dto.brand;

import com.zeynep.eTicaretSitesi.core.dao.BaseResponse;
import com.zeynep.eTicaretSitesi.core.entity.Brand;

public class BrandResponse extends BaseResponse<Brand> {

    private Long id;
    private String name;

    public BrandResponse(){}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}

