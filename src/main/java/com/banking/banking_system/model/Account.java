package com.banking.banking_system.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.banking.banking_system.enums.AccountStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity // indica que a classe será mapeada para uma tabela da base de dados
@Table(name = "accounts") 
public class Account {


    // atributos = colunas no Banco de Dados    
    @Id
    @GeneratedValue(strategy =GenerationType.UUID)
    private UUID accountId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customerId;

    @Column(nullable = false, unique=true, length = 30)
    private String accountNumber;

    @Column (nullable= false, precision= 19, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Version // Permite implementar Optimistic Locking
    private Long version;

    @Column(nullable = false)
    private String currency;

    @Enumerated (EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Column(nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    // JPA exige construtor vazio
    public Account(){} 

    // 
    @PrePersist 
    protected void onCreated(){
        this.createdAt = Instant.now();

        if(this.balance == null){
            this.balance = BigDecimal.ZERO;
        }

        if(this.currency.equals(null)){
            this.currency = "AKZ";
        }
    }


    // Getters e Setters
    public UUID getId() {
        return accountId;
    }

    // GET e SET acountNumber
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // GET e SET balance
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    // GET version
    public Long getVersion() {
        return version;
    }

    // GET createdAt
    public Instant getCreatedAt() {
        return createdAt;
    }

    // GET currency
    public String getCurrency() {
        return currency;
    }
    
    // GET customer
    public Customer getCustomer() {
        return customerId;
    }
}
