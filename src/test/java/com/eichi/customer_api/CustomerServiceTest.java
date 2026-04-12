package com.eichi.customer_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private CustomerRequestDTO dto;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("Alice");
        customer.setEmail("alice@example.com");
        customer.setPhone("0912345678");

        dto = new CustomerRequestDTO();
        dto.setName("Alice");
        dto.setEmail("alice@example.com");
        dto.setPhone("0912345678");
    }

    @Test
    void shouldCreateCustomerSuccessfully() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerService.createCustomer(dto);

        assertNotNull(result);
        assertEquals("Alice", result.getName());
        assertEquals("alice@example.com", result.getEmail());
        assertEquals("0912345678", result.getPhone());

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void shouldReturnAllCustomers() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());

        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnCustomerByIdWhenFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.getCustomerById(1L);

        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getName());

        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void shouldReturnEmptyOptionalWhenCustomerNotFound() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Customer> result = customerService.getCustomerById(99L);

        assertTrue(result.isEmpty());

        verify(customerRepository, times(1)).findById(99L);
    }

    @Test
    void shouldUpdateCustomerSuccessfully() {
        CustomerRequestDTO updateDto = new CustomerRequestDTO();
        updateDto.setName("Bob");
        updateDto.setEmail("bob@example.com");
        updateDto.setPhone("0987654321");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Customer result = customerService.updateCustomer(1L, updateDto);

        assertNotNull(result);
        assertEquals("Bob", result.getName());
        assertEquals("bob@example.com", result.getEmail());
        assertEquals("0987654321", result.getPhone());

        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingCustomer() {
        CustomerRequestDTO updateDto = new CustomerRequestDTO();
        updateDto.setName("Bob");
        updateDto.setEmail("bob@example.com");
        updateDto.setPhone("0987654321");

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                customerService.updateCustomer(1L, updateDto)
        );

        assertEquals("Customer not found with id: 1", exception.getMessage());

        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void shouldDeleteCustomerSuccessfully() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(1L);

        customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).existsById(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingCustomer() {
        when(customerRepository.existsById(1L)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                customerService.deleteCustomer(1L)
        );

        assertEquals("Customer not found with id: 1", exception.getMessage());

        verify(customerRepository, times(1)).existsById(1L);
        verify(customerRepository, never()).deleteById(anyLong());
    }

    @Test
    void shouldSearchCustomersByNameAndEmail() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.searchCustomers("Alice", "alice@example.com"))
                .thenReturn(customers);

        List<Customer> result = customerService.searchCustomers("Alice", "alice@example.com");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());

        verify(customerRepository, times(1))
                .searchCustomers("Alice", "alice@example.com");
    }

    @Test
    void shouldReturnEmptyListWhenNoSearchResults() {
        when(customerRepository.searchCustomers("NotFound", "none@example.com"))
                .thenReturn(Collections.emptyList());

        List<Customer> result = customerService.searchCustomers("NotFound", "none@example.com");

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(customerRepository, times(1))
                .searchCustomers("NotFound", "none@example.com");
    }

    @Test
    void shouldReturnPagedCustomers() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Customer> customers = Arrays.asList(customer);
        Page<Customer> customerPage = new PageImpl<>(customers, pageable, customers.size());

        when(customerRepository.searchCustomers("Alice", "alice@example.com", pageable))
                .thenReturn(customerPage);

        Page<Customer> result = customerService.getCustomers("Alice", "alice@example.com", pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Alice", result.getContent().get(0).getName());
        assertEquals(1, result.getTotalElements());

        verify(customerRepository, times(1))
                .searchCustomers("Alice", "alice@example.com", pageable);
    }
}