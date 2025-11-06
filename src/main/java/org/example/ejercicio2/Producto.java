package org.example.ejercicio2;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals, hashCode
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;
}