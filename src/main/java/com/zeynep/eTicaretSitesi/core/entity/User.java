package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")


public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String password;
    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    public User () {
    }
    public String getFirstName(){
    return firstName;
    }
    public void setFirstName(String firstName){
    this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;}
    public void setPassword(String password){
        this.password = password;
    }

    public LocalDate getBirthDate(){
        return birthDate; }
    public void setBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
    }
}
