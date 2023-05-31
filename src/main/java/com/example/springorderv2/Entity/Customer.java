package com.example.springorderv2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long id;

    private String name;

    private String personalNumber;

    public Customer(String name, String personalNumber) {
        this.name = name;
        this.personalNumber = personalNumber;
    }
}
