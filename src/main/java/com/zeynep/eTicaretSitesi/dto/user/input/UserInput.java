package com.zeynep.eTicaretSitesi.dto.user.input;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.User;

import java.time.LocalDate;

public class UserInput extends BaseInput<User> {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String phoneNumber;

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){ return lastName; }
    public void setLastName(String lastName){this.lastName =  lastName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email= email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password= password;}

    public LocalDate getBirthDate() { return birthDate;}
    public void setBirthDate(LocalDate birthDate){this.birthDate=birthDate;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber= phoneNumber;}

}
