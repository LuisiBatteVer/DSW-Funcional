package org.example.ejercicio1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase principal (Main) para ejecutar y probar el Caso Práctico 1: Alumno,
 * aplicando la Programación Funcional (Java Stream API).
 */
public class Main {

    public static void main(String[] args) {

        // --- 1. Inicialización de Datos (Lista de Alumnos) ---
        List<Alumno> alumnos = Arrays.asList(
                // Promedio del alumno es calculado internamente por Alumno::getPromedio()
                new Alumno("Carlos", "A", Arrays.asList(8.0f, 7.5f, 9.0f)),      // ~8.17
                new Alumno("Ana", "B", Arrays.asList(5.0f, 6.5f, 7.0f)),        // ~6.17
                new Alumno("David", "A", Arrays.asList(10.0f, 9.5f, 9.0f)),     // 9.50
                new Alumno("Elena", "C", Arrays.asList(7.0f, 7.0f, 7.0f)),      // 7.0
                new Alumno("Bernardo", "B", Arrays.asList(10.0f, 10.0f)),       // 10.0
                new Alumno("Francisco", "C", Arrays.asList(5.0f, 5.0f)),        // 5.0
                new Alumno("Gabriel", "A", Arrays.asList(8.5f, 9.5f))           // 9.0
        );

        System.out.println("--- Caso Practico 1 (Alumno): Solución Funcional ---\n");

        resolverPunto1(alumnos);
        resolverPunto2(alumnos);
        resolverPunto3(alumnos);
        resolverPunto4(alumnos);
    }

    // --- SOLUCIONES FUNCIONALES (MÉTODOS PRIVADOS) ---

    /**
     * 1. Obtener los nombres de los alumnos aprobados (promedio ≥ 7) en mayúsculas y ordenados.
     */
    private static void resolverPunto1(List<Alumno> alumnos) {
        List<String> nombresAprobados = alumnos.stream()
                .filter(a -> a.getPromedio() >= 7)       // Filtra los aprobados
                .map(Alumno::getNombre)                  // Obtiene el nombre
                .map(String::toUpperCase)                // Convierte a mayúsculas
                .sorted()                                // Ordena alfabéticamente
                .collect(Collectors.toList());           // Recolecta en una lista

        System.out.println("1. Nombres de aprobados (Promedio >= 7):");
        System.out.println("Resultado: " + nombresAprobados + "\n");
    }

    /**
     * 2. Calcular el promedio general de notas.
     */
    private static void resolverPunto2(List<Alumno> alumnos) {
        double promedioGeneral = alumnos.stream()
                // Mapea cada alumno a su promedio de nota (DoubleStream)
                .mapToDouble(Alumno::getPromedio)
                .average()                          // Calcula el promedio de todos los promedios
                .orElse(0.0);

        System.out.println("2. Promedio general de notas de todos los alumnos:");
        System.out.printf("Resultado: %.2f\n\n", promedioGeneral);
    }

    /**
     * 3. Agrupar alumnos por curso usando Collectors.groupingBy().
     */
    private static void resolverPunto3(List<Alumno> alumnos) {
        // GroupingBy: Agrupa los alumnos usando el curso como clave del Map.
        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));

        System.out.println("3. Alumnos agrupados por curso:");
        alumnosPorCurso.forEach((curso, lista) ->
                System.out.println("  Curso " + curso + ": " + lista.stream()
                        // Mapea los alumnos a un string y los une con ", " (Collectors.joining)
                        .map(a -> a.getNombre() + " (Promedio: " + String.format("%.2f", a.getPromedio()) + ")")
                        .collect(Collectors.joining(", ")))
        );
        System.out.println();
    }

    /**
     * 4. Obtener los 3 mejores promedios.
     */
    private static void resolverPunto4(List<Alumno> alumnos) {
        List<Alumno> top3 = alumnos.stream()
                // Ordena por promedio descendente (reversed)
                .sorted(Comparator.comparing(Alumno::getPromedio).reversed())
                .limit(3) // Limita el Stream a los 3 primeros
                .collect(Collectors.toList());

        System.out.println("4. Los 3 alumnos con los mejores promedios:");
        top3.forEach(a -> System.out.println("  - " + a.getNombre() +
                " (Promedio: " + String.format("%.2f", a.getPromedio()) + ")"));
        System.out.println();
    }
}