package com.example.springorderv2.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ordrar {
    @Id
    @GeneratedValue
    private Long id;

    @GeneratedValue
    @CreatedDate
    private LocalDateTime createdDate;

    @GeneratedValue
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    private Long customer;

    private Long item;

    public Ordrar(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Ordrar(Long id) {
        this.id = id;
    }

    public Long getCustomer() {
        return customer;
    }

    public Long getItem() {
        return item;
    }

    @PrePersist
    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void setModifiedDate() {
        this.lastModifiedDate = LocalDateTime.now();
    }
}
