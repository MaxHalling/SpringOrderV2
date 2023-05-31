package com.example.springorderv2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {

    private Long id;

    private String name;

    private double price;

    public Items(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
