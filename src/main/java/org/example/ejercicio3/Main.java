package org.example.ejercicio3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase principal (Main) para ejecutar el Caso Practico 3: Libro.
 * Aplica la Programacion Funcional (Java Stream API).
 */
public class Main {

    public static void main(String[] args) {

        // --- 1. Inicializacion de Datos (Lista de Libros) ---
        List<Libro> libros = Arrays.asList(
                new Libro("El Codigo Limpio", "Robert C. Martin", 464, 35.50),
                new Libro("Patrones de Diseno", "Erich Gamma", 416, 45.99),
                new Libro("La Historia Interminable", "Michael Ende", 528, 20.00),
                new Libro("Cien Anos de Soledad", "Gabriel Garcia Marquez", 496, 28.75),
                new Libro("El Gran Gatsby", "F. Scott Fitzgerald", 180, 15.20),
                new Libro("Java 8 in Action", "Raoul-Gabriel Urma", 418, 55.00),
                new Libro("El Hobbit", "J.R.R. Tolkien", 310, 18.50)
        );

        System.out.println("--- Caso Practico 3 (Libro): Solucion Funcional ---\n");

        resolverPunto1(libros);
        resolverPunto2(libros);
        resolverPunto3(libros);
        resolverPunto4(libros);
    }

    // --- SOLUCIONES FUNCIONALES (REQUERIMIENTOS) ---

    /**
     * 1. Listar los titulos de los libros con mas de 300 paginas, ordenados alfabeticamente.
     */
    private static void resolverPunto1(List<Libro> libros) {
        List<String> titulosLargos = libros.stream()
                .filter(l -> l.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("1. Titulos de libros con mas de 300 paginas, ordenados:");
        titulosLargos.forEach(t -> System.out.println("  - " + t));
        System.out.println();
    }

    /**
     * 2. Calcular el promedio de paginas de todos los libros.
     */
    private static void resolverPunto2(List<Libro> libros) {
        double promedioPaginas = libros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);

        System.out.println("2. Promedio de paginas de todos los libros:");
        System.out.printf("Resultado: %.2f paginas\n\n", promedioPaginas);
    }

    /**
     * 3. Agrupar los libros por autor y contar cuantos libros tiene cada uno.
     */
    private static void resolverPunto3(List<Libro> libros) {
        Map<String, Long> conteoPorAutor = libros.stream()
                .collect(
                        Collectors.groupingBy(
                                Libro::getAutor,
                                Collectors.counting()       // Utiliza counting() para contar
                        )
                );

        System.out.println("3. Conteo de libros por autor:");
        conteoPorAutor.forEach((autor, conteo) ->
                System.out.println("  - " + autor + ": " + conteo + " libro(s)"));
        System.out.println();
    }

    /**
     * 4. Obtener el libro mas caro de la lista.
     */
    private static void resolverPunto4(List<Libro> libros) {
        Optional<Libro> libroMasCaro = libros.stream()
                .max(Comparator.comparing(Libro::getPrecio)); // max() con Comparator

        System.out.println("4. El libro mas caro de la lista:");

        libroMasCaro.ifPresent(libro ->
                System.out.printf("  - Titulo: %s, Autor: %s, Precio: %.2f\n",
                        libro.getTitulo(), libro.getAutor(), libro.getPrecio())
        );

        if (libroMasCaro.isEmpty()) {
            System.out.println("La lista de libros esta vacia.");
        }
    }
}