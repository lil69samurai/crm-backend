package com.eichi.customer_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name can not be empty.")
    @Size(min = 2, max = 50,message = "Must be in 2~50 letters.")
    private String name;
    @NotBlank(message = "Email can not be empty.")
    @Email(message = "Email format is incorrect.")
    private String email;
    @NotBlank(message = "Phone can not be empty.")
    @Pattern(regexp = "^0[789]0\\d{8}$",message = "Must be start with 070/080/090 ,11 numbers.")
    private String phone;

    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
}

git add .
git commit -m "feat: add global exception handler"
git push