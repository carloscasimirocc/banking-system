package com.banking.banking_system.dto;

import java.time.Instant;
import java.util.UUID;

public class CustomerResponseDTO {

    private UUID id;
    private String fullname;
    private String email;
    private String phone;
    private Instant createdAt;

    public CustomerResponseDTO(UUID id, String fullname, String email, 
                                    String phone, Instant createdAt) {
        
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public CustomerResponseDTO() {
    }

    public UUID getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

}
