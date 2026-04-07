package com.eichi.customer_api;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    //Create — 新增客戶
    public Customer createCustomer(CustomerRequestDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        return customerRepository.save(customer);
    }
    //Search — 查詢客戶
    public List<Customer> getAllCustomers(){
            return customerRepository.findAll();
    }
    //Read One — 依ID查詢
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    //Update — 修改客戶
    public Customer updateCustomer(Long id, CustomerRequestDTO dto) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());

        return customerRepository.save(existing);
    }
    //Delete — 刪除客戶
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("ID not found: " + id);
        }
        customerRepository.deleteById(id);
    }
    //Search
    public List<Customer> searchCustomers(String name, String email){
        return customerRepository.searchCustomers(name, email);
    }

    //Search+Pagination
    public Page<Customer> getCustomers(String name, String email, Pageable pageable){
        return customerRepository.searchCustomers(name, email, pageable);
    }
}



