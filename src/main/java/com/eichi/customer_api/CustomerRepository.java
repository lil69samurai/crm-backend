package com.eichi.customer_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
    List<Customer> findByNameContaining(String name);
    List<Customer> findByEmailContaining(String email);

    @Query("SELECT c FROM Customer c WHERE " +
            "(:name IS NULL OR c.name LIKE %:name%) AND " +
            "(:email IS NULL OR c.email LIKE %:email%)")
    List<Customer> searchCustomer(@Param("name") String name,
                                  @Param("email") String email);
}