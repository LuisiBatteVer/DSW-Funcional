package org.example.ejercicio1;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Alumno {
    private String nombre;
    private String curso;
    private final List<Float> notas = new ArrayList<>();

    // Constructor que acepta la lista de notas
    public Alumno(String nombre, String curso, List<Float> notas) {
        this.nombre = nombre;
        this.curso = curso;
        this.notas.addAll(notas); // Copiamos las notas
    }

    /**
     * Calcula el promedio de las notas de forma funcional (getPromedio() es clave).
     */
    public Double getPromedio(){
        return notas.stream()
                .mapToDouble(Float::doubleValue)
                .average()
                .orElse(0.0);
    }
}