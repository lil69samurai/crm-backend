package com.eichi.customer_api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    //Accept RequestDTO, Return ResponseDTO
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(
            @Valid @RequestBody CustomerRequestDTO dto){
        Customer created = customerService.createCustomer(dto);
        return ResponseEntity.status(201).body(new CustomerResponseDTO(created));
    }
    //Return ResponseDTO List
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email) {
        List<Customer> customers;

        if (name != null ||email != null){
        customers = customerService.searchCustomers(name,email);
        } else {
            customers = customerService.getAllCustomers();
        }

            List<CustomerResponseDTO> response = customers.stream()
                    .map(CustomerResponseDTO::new)
                    .toList();

            return ResponseEntity.ok(response);
        }

    //Return RequestDTO
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id)
                .map(customer -> ResponseEntity.ok(new CustomerResponseDTO(customer)))
                .orElse(ResponseEntity.notFound().build());
    }
    //Accept RequestDTO
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDTO dto){
        try {
            Customer updated = customerService.updateCustomer(id, dto);
            return ResponseEntity.ok(new CustomerResponseDTO(updated));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCostomer(@PathVariable Long id){
        try{
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
