package org.example.ejercicio4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // --- 1. Inicializacion de Datos (Lista de Empleados) ---
        List<Empleado> empleados = Arrays.asList(
                new Empleado("Ana Lopez", "IT", 2500.0, 30),
                new Empleado("Juan Perez", "Ventas", 1800.0, 45),
                new Empleado("Elena Ruiz", "IT", 3200.0, 25),
                new Empleado("Pedro Gomez", "RRHH", 2100.0, 35),
                new Empleado("Laura Diaz", "Ventas", 2500.0, 28),
                new Empleado("Miguel Sanz", "IT", 1900.0, 40),
                new Empleado("Sofia Castro", "RRHH", 2800.0, 22) // Empleada mas joven
        );

        System.out.println("--- Caso Practico 4 (Empleado): Solucion Funcional ---\n");

        resolverPunto1(empleados);
        resolverPunto2(empleados);
        resolverPunto3(empleados);
        resolverPunto4(empleados);
    }

    // --- SOLUCIONES FUNCIONALES (REQUERIMIENTOS) ---

    /**
     * 1. Obtener la lista de empleados cuyo salario sea mayor a 2000,
     * ordenados por salario descendente.
     * Pipeline: filter -> sorted -> collect
     */
    private static void resolverPunto1(List<Empleado> empleados) {
        List<Empleado> altosSalarios = empleados.stream()
                .filter(e -> e.getSalario() > 2000.0)
                // Ordena por salario y luego revierte (descendente)
                .sorted(Comparator.comparing(Empleado::getSalario).reversed())
                .collect(Collectors.toList());

        System.out.println("1. Empleados con salario > 2000, ordenados descendente:");
        altosSalarios.forEach(e ->
                System.out.printf("  - %s (%s): %.2f\n", e.getNombre(), e.getDepartamento(), e.getSalario()));
        System.out.println();
    }

    /**
     * 2. Calcular el salario promedio general.
     * Reduccion: mapToDouble -> average
     */
    private static void resolverPunto2(List<Empleado> empleados) {
        double salarioPromedio = empleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);

        System.out.println("2. Salario promedio general:");
        System.out.printf("Resultado: %.2f\n\n", salarioPromedio);
    }

    /**
     * 3. Agrupar los empleados por departamento y calcular la suma de salarios de cada uno.
     * Agrupacion: groupingBy con downstream collector (summingDouble)
     */
    private static void resolverPunto3(List<Empleado> empleados) {
        // GroupingBy(Clave, Downstream Collector)
        Map<String, Double> sumaSalariosPorDepto = empleados.stream()
                .collect(
                        Collectors.groupingBy(
                                Empleado::getDepartamento,
                                Collectors.summingDouble(Empleado::getSalario) // Suma de salarios por grupo
                        )
                );

        System.out.println("3. Suma de salarios por departamento:");
        sumaSalariosPorDepto.forEach((depto, suma) ->
                System.out.printf("  - %s: %.2f\n", depto, suma));
        System.out.println();
    }

    /**
     * 4. Obtener los nombres de los 2 empleados mas jovenes.
     * Pipeline: sorted -> limit -> map -> collect
     */
    private static void resolverPunto4(List<Empleado> empleados) {
        List<String> nombresMasJovenes = empleados.stream()
                // Ordena por edad ascendente (mas jovenes primero)
                .sorted(Comparator.comparing(Empleado::getEdad))
                .limit(2) // Limita a los 2 primeros
                .map(Empleado::getNombre) // Mapea solo al nombre
                .collect(Collectors.toList());

        System.out.println("4. Nombres de los 2 empleados mas jovenes:");
        nombresMasJovenes.forEach(nombre ->
                System.out.println("  - " + nombre));
        System.out.println();
    }
}