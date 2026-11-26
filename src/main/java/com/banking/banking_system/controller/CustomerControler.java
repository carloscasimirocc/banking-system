package com.banking.banking_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.banking_system.dto.CustomerRequestDTO;
import com.banking.banking_system.dto.CustomerResponseDTO;
import com.banking.banking_system.model.Customer;
import com.banking.banking_system.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerControler {

    private final CustomerService customerService;

    public CustomerControler(CustomerService customerService) {

        this.customerService = customerService;

    }

    @PostMapping("/api/v1/customer/create")

    public ResponseEntity<CustomerResponseDTO> registerCustomer(
            @Valid @RequestBody CustomerRequestDTO request) {

        Customer customer = customerService.createCustomer(
                request.getFullname(),
                request.getEmail(),
                request.getPhone());

        CustomerResponseDTO response = customerService.toResponseDTO(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
