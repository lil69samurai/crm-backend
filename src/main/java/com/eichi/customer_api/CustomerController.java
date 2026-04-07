package com.eichi.customer_api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

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
    public ResponseEntity<Page<CustomerResponseDTO>> getAllCustomers(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10")int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customers = customerService.getCustomers(name, email, pageable);
        Page<CustomerResponseDTO> response = customers.map(CustomerResponseDTO::new);
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
