package com.banking.banking_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerRequestDTO {

@NotBlank(message = "Full name is required") 
@Size(max = 150, message = "Full name can not exceed 150 character")
private String fullname;

@Size(max = 150, message = "Email can not excee 150 characters") 
@NotBlank(message = "Email is required")
@Email(message = "Email must be valid") 
private String email;

@Size(max = 20, message = "Phon can not exceed 20 characters") 
private String phone;

public CustomerRequestDTO(String fullname, String email, String phone){
    this.fullname = fullname;
    this.email = email;
    this.phone = phone;
}

public CustomerRequestDTO(){}


public String getFullname() {
    return fullname;
}

public String getEmail() {
    return email;
}

public String getPhone() {
    return phone;
}



    
}
