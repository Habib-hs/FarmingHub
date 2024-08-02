package com.project.farmingHub.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    String Name;
}
