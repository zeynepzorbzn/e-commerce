package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import com.zeynep.eTicaretSitesi.core.enums.RoleName;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;

    public Role() {
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}