package org.example.ejercicio4;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    private String nombre;
    private String departamento;
    private double salario;
    private int edad;
}