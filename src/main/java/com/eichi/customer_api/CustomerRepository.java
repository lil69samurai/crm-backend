package com.eichi.customer_api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
    List<Customer> findByNameContaining(String name);
    List<Customer> findByEmailContaining(String email);

    //Search
    @Query("SELECT c FROM Customer c WHERE " +
            "(:name IS NULL OR c.name LIKE %:name%) AND " +
            "(:email IS NULL OR c.email LIKE %:email%)")
    List<Customer> searchCustomers(@Param("name") String name,
                                  @Param("email") String email);

    //Search(Pages)
    @Query("SELECT c FROM Customer c WHERE " +
            "(:name IS NULL OR c.name LIKE %:name%) AND " +
            "(:email IS NULL OR c.email LIKE %:email%)")
    Page<Customer> searchCustomers(@Param("name") String name,
                                  @Param("email") String email,
                                  Pageable pageable);
}