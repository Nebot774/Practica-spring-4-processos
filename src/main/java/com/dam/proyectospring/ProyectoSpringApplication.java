package com.dam.proyectospring;

import com.dam.proyectospring.modelos.PilotoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.dam.proyectospring.modelos.Piloto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Scanner;

@SpringBootApplication
public class ProyectoSpringApplication {

    static PilotoClient pilotoClient = new PilotoClient("http://localhost:8080");
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {


        boolean continuar = true;
        do {
            mostrarMenu();
            int opcion = obtenerOpcionValida();
            switch (opcion) {
                case 1:
                    mostrarTodosLosPilotos();
                    break;
                case 2:
                    mostrarPilotoPorId();
                    break;
                case 3:
                    crearPiloto();
                    break;
                case 4:
                    actualizarPiloto();
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no reconocida. Intenta de nuevo.");
            }
        } while (continuar);
    }

        private static void mostrarMenu() {
            System.out.println("\nGestión de Pilotos:");
            System.out.println("1. Mostrar todos los pilotos");
            System.out.println("2. Mostrar un piloto por ID");
            System.out.println("3. Crear un piloto");
            System.out.println("4. Actualizar un piloto");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
        }

        private static int obtenerOpcionValida() {
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduce un número.");
                scanner.next(); // descartar la entrada no válida
            }
            return scanner.nextInt();
        }

        private static void mostrarTodosLosPilotos() {
            Flux<Piloto> pilotos = pilotoClient.getAllPilotos();
            pilotos.subscribe(
                    piloto -> System.out.println(piloto),
                    error -> System.out.println("Error al recuperar los pilotos: " + error.getMessage())
            );
        }

    private static void mostrarPilotoPorId() {
        System.out.print("Ingresa el ID del piloto: ");
        scanner.nextLine(); // Consumir el salto de línea restante
        String idStr = scanner.nextLine();

        // Convertir String a Long
        Long id = Long.parseLong(idStr);

        Mono<Piloto> pilotoMono = pilotoClient.getPiloto(id);
        pilotoMono.subscribe(
                piloto -> System.out.println(piloto),
                error -> System.out.println("Error al recuperar el piloto: " + error.getMessage())
        );
    }


    private static void crearPiloto() {
            Piloto nuevoPiloto = new Piloto();
            // Aquí recogerías los datos del piloto, por ejemplo:
            System.out.print("Nombre del piloto: ");
            scanner.nextLine(); // Consumir el salto de línea restante
            nuevoPiloto.setNombre(scanner.nextLine());
            // Continúa recogiendo otros datos...

            Mono<Piloto> pilotoCreado = pilotoClient.addPiloto(nuevoPiloto);
            pilotoCreado.subscribe(
                    piloto -> System.out.println("Piloto creado: " + piloto),
                    error -> System.out.println("Error al crear el piloto: " + error.getMessage())
            );
        }

    private static void actualizarPiloto() {
        System.out.print("ID del piloto a actualizar: ");
        scanner.nextLine(); // Consumir el salto de línea restante
        String idStr = scanner.nextLine();

        // Convertir String a Long
        Long id = Long.parseLong(idStr);

        Piloto pilotoActualizado = new Piloto();
        // Recoger los nuevos datos del piloto...
        System.out.print("Nuevo nombre del piloto: ");
        pilotoActualizado.setNombre(scanner.nextLine());

        // Otros datos del piloto...

        Mono<Piloto> pilotoActualizadoMono = pilotoClient.updatePiloto(id, pilotoActualizado);
        pilotoActualizadoMono.subscribe(
                piloto -> System.out.println("Piloto actualizado: " + piloto),
                error -> System.out.println("Error al actualizar el piloto: " + error.getMessage())
        );
    }



}