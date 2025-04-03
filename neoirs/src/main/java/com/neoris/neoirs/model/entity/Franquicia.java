package com.neoris.neoirs.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// trae todos los setters, getters, equals y operaciones que funcionan para la entidad
@Data 
// genera el contructor con todos los argumentos y sin argumentos 
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "franquicia")
public class Franquicia implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    
}
