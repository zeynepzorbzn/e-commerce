package com.zeynep.eTicaretSitesi.core.dao;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    // OOP -> Inheritance / Abstraction / Polymorphism / Composition / Encapsulation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BaseEntity(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}



