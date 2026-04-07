package com.eichi.customer_api;

import jakarta.validation.constraints.*;

public class CustomerRequestDTO {
    @NotBlank(message = "Name can't be empty.")
    @Size(min = 2,max = 50,message = "Must be in 2~50 letters.")
    private String name;

    @NotBlank(message = "Email can't be empty.")
    @Email(message = "Email fprmat is incorrect")
    private String email;

    @NotBlank(message = "Phone can't be empty")
    @Pattern(regexp = "^0[789]0\\d{8}$", message = "Must start with 070/080/090, 11 numbers.")
    private String phone;

    //Getters
    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPhone(){return phone;}

    //Setters
    public void setName(String name){this.name = name;}
    public void setEmail(String email){this.email = email;}
    public void setPhone(String phone){this.phone = phone;}
}
