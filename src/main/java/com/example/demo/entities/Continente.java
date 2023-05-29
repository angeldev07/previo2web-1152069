package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Continente
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "continente")
public class Continente {

    @Id
    private int id;
    private String nombre;
    
}