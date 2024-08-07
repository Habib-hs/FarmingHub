package com.project.farmingHub.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    int id;
    String Name;
}
