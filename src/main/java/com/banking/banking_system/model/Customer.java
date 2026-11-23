package com.banking.banking_system.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.mapping.List;
//import org.hibernate.validator.constraints.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity 
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID) 
    private UUID custumerId;

    @OneToMany(mappedBy = "customerId", 
                cascade = CascadeType.ALL, 
                orphanRemoval = true)
    private List<Account> accountsList = new ArrayList<>();
    

    @Column(nullable = false)
    private String fullname;

    @Email 
    @Column (nullable = false, unique=true, length = 150)
    private String email;

    @Column (length = 20)
    private String phone;

    @Column (nullable=false, updatable = false)
    @CreationTimestamp 
    private Instant createdAt;

    public Customer() {}

    @PrePersist 
    protected void onCreated(){
        this.createdAt = Instant.now(); }

    // ================= GETTERS =========================
    
    public UUID getId() {
        return custumerId;
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
    
    public Instant getCreaatedAt() {
        return createdAt;
    }

    // ================= SETTERS =========================
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
