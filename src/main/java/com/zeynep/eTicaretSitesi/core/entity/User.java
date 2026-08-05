package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    private String firstName;
    private String lastName;
    private String password;
    private LocalDate birthDate;
    private String phoneNumber;
    private String emailVerificationToken;
    private Boolean emailVerified = false;
    private LocalDateTime emailVerificationExpireDate;
    private Boolean locked = false;
    @Column(unique = true, nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Address> addresses;
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Cart cart;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PaymentMethod> paymentMethods;
    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY)
    private Store store;


    public User () {
    }
    public String getFirstName(){
    return firstName;
    }
    public void setFirstName(String firstName){
    this.firstName = firstName;
    }

    public String getLastName(){return lastName;}
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){return password;}
    public void setPassword(String password){
        this.password = password;
    }

    public LocalDate getBirthDate(){return birthDate; }
    public void setBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
    }

    public Role getRole() {return role;}
    public void setRole(Role role) {this.role = role;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getEmailVerificationToken() {return emailVerificationToken;}
    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;}

    public Boolean getEmailVerified() {return emailVerified;}
    public void setEmailVerified(Boolean emailVerified) {this.emailVerified = emailVerified;}

    public LocalDateTime getEmailVerificationExpireDate() {return emailVerificationExpireDate;}
    public void setEmailVerificationExpireDate(LocalDateTime emailVerificationExpireDate) {
        this.emailVerificationExpireDate = emailVerificationExpireDate;}

    public Boolean getLocked() {return locked;}
    public void setLocked(Boolean locked) {this.locked = locked;}

    public Cart getCart() {return cart;}
    public void setCart(Cart cart) {this.cart = cart;}

    public List<Address> getAddresses() {return addresses;}
    public void setAddresses(List<Address> addresses){this.addresses = addresses;}

    public List<PaymentMethod> getPaymentMethod() {return paymentMethods;}
    public void setPaymentMethod(List<PaymentMethod> paymentMethods) {this.paymentMethods = paymentMethods;}

    public Store getStore(){return store;}
    public void setStore(Store store){this.store = store;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));}

    @Override
    public String getUsername() {return email;}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}


}
