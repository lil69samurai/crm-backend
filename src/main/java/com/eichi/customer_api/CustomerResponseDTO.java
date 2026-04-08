package com.eichi.customer_api;

import io.swagger.v3.oas.annotations.media.Schema;

public class CustomerResponseDTO {
    @Schema(description = "Customer ID", example = "1")
    private Long id;
    @Schema(description = "Customer name", example = "田中太郎")
    private String name;
    @Schema(description = "Customer email", example = "tanaka@test.com")
    private String email;
    @Schema(description = "Customer phone number", example = "09012345678")
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
