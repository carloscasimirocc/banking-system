package com.banking.banking_system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.banking.banking_system.exception.CustomerAlreadyExistsException;
import com.banking.banking_system.model.Customer;
import com.banking.banking_system.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void ShouldCreateCustomerSuccessFully() {

        // Arrange -> preparar condições e comportamentos.

        when(customerRepository.existsByEmail("ana@example.com"))
                .thenReturn(false);

        when(customerRepository.save(any(Customer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act -> executar o método que queremos testar.

        Customer result = customerService.createCustomer(
                "Ana Silva",
                "ana@example.com",
                "321456763");

        // Assert -> verificar se o resultado é o esperado

        assertNotNull(result);
        assertEquals("Ana Silva", result.getFullname());
        assertEquals("ana@example.com", result.getEmail());
        assertEquals("321456763", result.getPhone());

        verify(customerRepository).existsByEmail("ana@example.com");
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void shoulThrowExceptionEmailAlreadyExists() {
        // Arrange -> preparar condições e comportamentos.

        when(customerRepository.existsByEmail("ana@example.com"))
                .thenReturn(true);

        // Act & Assets -> executar o método que queremos testar.
        assertThrows(
                CustomerAlreadyExistsException.class,
                () -> customerService.createCustomer(
                        "Ana Silva",
                        "ana@example.com",
                        "321876532"));

        verify(customerRepository, never()).save(any(Customer.class));

    }

    @Test 
    void shouldNormalizeEmailWhenCreatingCustomer() {

        // Arrange -> preparar condições e comportamentos.
        when(customerRepository.existsByEmail("ana@example.com"))
                .thenReturn(false);

        when(customerRepository.save(any(Customer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0)); 
                
        // Act -> executar o método que queremos testar.

        Customer result = customerService.createCustomer(
            "Ana Silva", 
            "  ANA @ EXAMPLE.COM  ", 
            "432234432");

        // Assert -> verificar se o resultado é o esperado

        assertEquals("ana@example.com", result.getEmail());

        verify(customerRepository).existsByEmail("ana@example.com");
        verify(customerRepository).save(any(Customer.class));

        //result.getEmail()

    }
}

    
