package com.eichi.customer_api;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    //Create — 新增客戶
    public Customer createCustomer(Customer customer) {
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
    public Customer updateCustomer(Long id, Customer updatedData) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("找不到客戶 ID: " + id));

        existing.setName(updatedData.getName());
        existing.setEmail(updatedData.getEmail());
        existing.setPhone(updatedData.getPhone());

        return customerRepository.save(existing);
    }
    //Delete — 刪除客戶
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("找不到客戶 ID: " + id);
        }
        customerRepository.deleteById(id);
    }

}



