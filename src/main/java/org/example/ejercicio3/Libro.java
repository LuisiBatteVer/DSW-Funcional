package org.example.ejercicio3;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private String titulo;
    private String autor;
    private int paginas;
    private double precio;
}