package com.banking.banking_system.service;

import java.util.Locale;

import org.springframework.stereotype.Service;

import com.banking.banking_system.exception.CustomerAlreadyExistsException;
import com.banking.banking_system.model.Customer;
import com.banking.banking_system.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer createCustomer(String fullname, String email, String phone) {
        String normalizedEmail = email.toLowerCase(Locale.ROOT)
                .replaceAll("\\s+", "");

        if (customerRepository.existsByEmail(normalizedEmail)) {
            throw new CustomerAlreadyExistsException(normalizedEmail);
        }

        Customer customer = new Customer();

        customer.setEmail(normalizedEmail);
        customer.setFullname(fullname);
        customer.setPhone(phone);

        return customerRepository.save(customer);
    }

}
