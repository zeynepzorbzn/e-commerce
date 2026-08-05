package com.zeynep.eTicaretSitesi.core.dao;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    // OOP -> Inheritance / Abstraction / Polymorphism / Composition / Encapsulation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Boolean enabled = true;
    private Boolean deleted = false;

    public BaseEntity(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();}
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}



