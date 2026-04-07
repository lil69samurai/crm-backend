package com.eichi.customer_api;

public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;

    //Customer Entity->DTO
    public CustomerResponseDTO(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
    }
    //Getters
    public Long getId(){return id;}
    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPhone(){return phone;}

}
