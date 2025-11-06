package org.example.ejercicio2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // --- 1. Inicialización de Datos (Lista de Productos) ---
        List<Producto> productos = Arrays.asList(
                new Producto("Laptop Pro", "Electronica", 1200.0, 15),
                new Producto("Teclado Mecánico", "Electronica", 120.0, 50),
                new Producto("Silla Ergonómica", "Oficina", 250.0, 20),
                new Producto("Cuaderno A4", "Oficina", 10.0, 200),
                new Producto("Mouse Inalámbrico", "Electronica", 80.0, 75),
                new Producto("Monitor 27", "Electronica", 350.0, 10),
                new Producto("Boligrafo Azul", "Oficina", 2.5, 500)
        );

        System.out.println("--- Caso Practico 2 (Producto): Solucion Funcional ---\n");

        resolverPunto1(productos);
        resolverPunto2(productos);
        resolverPunto3(productos);
        resolverPunto4(productos);
    }

    // --- SOLUCIONES FUNCIONALES (REQUERIMIENTOS) ---

    /**
     * 1. Listar productos con precio mayor a 100, ordenados por precio descendente.
     * Pipeline: filter -> sorted -> collect
     */
    private static void resolverPunto1(List<Producto> productos) {
        List<Producto> filtradosOrdenados = productos.stream()
                .filter(p -> p.getPrecio() > 100)
                // Ordena por precio y luego revierte el orden (descendente)
                .sorted(Comparator.comparing(Producto::getPrecio).reversed())
                .collect(Collectors.toList());

        System.out.println("1. Productos con precio > 100, ordenados descendente:");
        filtradosOrdenados.forEach(p ->
                System.out.printf("  - %s (Precio: %.2f)\n", p.getNombre(), p.getPrecio()));
        System.out.println();
    }

    /**
     * 2. Agrupar productos por categoría y calcular el stock total.
     * Agrupación: groupingBy con downstream collector (summingInt)
     */
    private static void resolverPunto2(List<Producto> productos) {
        // groupingBy(Clave, Downstream Collector)
        Map<String, Integer> stockTotalPorCategoria = productos.stream()
                .collect(
                        Collectors.groupingBy(
                                Producto::getCategoria,            // Clave: Categoría
                                Collectors.summingInt(Producto::getStock) // Valor: Suma del stock por grupo
                        )
                );

        System.out.println("2. Stock total por categoria:");
        stockTotalPorCategoria.forEach((categoria, totalStock) ->
                System.out.println("  - Categoraa " + categoria + ": Stock Total = " + totalStock));
        System.out.println();
    }

    /**
     * 3. Generar un String separando con ";" cada producto que contenga nombre y precio,
     * usando map + collect(joining).
     */
    private static void resolverPunto3(List<Producto> productos) {
        String listaSeparada = productos.stream()
                // Mapea el objeto Producto a un String personalizado (Nombre: Precio)
                .map(p -> String.format("%s:%.2f", p.getNombre(), p.getPrecio()))
                // Une todos los Strings resultantes con el delimitador ";"
                .collect(Collectors.joining("; "));

        System.out.println("3. Productos listados con nombre y precio, separados por ';':");
        System.out.println("Resultado: " + listaSeparada + "\n");
    }

    /**
     * 4. Calcular el precio promedio general y por categoría.
     */
    private static void resolverPunto4(List<Producto> productos) {
        // Promedio General (mapToDouble -> average)
        double promedioGeneral = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);

        // Promedio por Categoría (groupingBy con downstream collector averagingDouble)
        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(
                        Collectors.groupingBy(
                                Producto::getCategoria,            // Clave: Categoría
                                Collectors.averagingDouble(Producto::getPrecio) // Valor: Promedio por grupo
                        )
                );

        System.out.println("4. Precio promedio:");
        System.out.printf("  - Promedio General: %.2f\n", promedioGeneral);
        System.out.println("  - Promedio por Categoría:");
        promedioPorCategoria.forEach((categoria, promedio) ->
                System.out.printf("    * %s: %.2f\n", categoria, promedio));
        System.out.println();
    }
}