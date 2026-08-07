package com.zeynep.eTicaretSitesi.dto.user.input;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.core.enums.RoleName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class UserInput extends BaseInput<User> {

    @NotBlank(message = "Ad boş olamaz.")
    private String firstName;
    @NotBlank(message = "Soyad boş olamaz.")
    private String lastName;
    @Email(message = "Geçerli bir email giriniz.")
    @NotBlank(message = "Email boş olamaz.")
    private String email;
    @NotBlank(message = "Şifre boş olamaz.")
    @Size(min = 6, message = "Şifre en az 6 karakter olmalıdır.")
    private String password;
    @NotNull(message = "Doğum tarihi zorunludur.")
    private LocalDate birthDate;
    @NotNull(message = "Telefon numarası zorunludur.")
    private String phoneNumber;
    private RoleName role;

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

    public RoleName getRole() {return role;}
    public void setRole(RoleName role) {this.role = role;}
}
