# Sistema Avanzado de Administración de Pilotos

## Resumen del Proyecto
Este sistema integral se bifurca en dos componentes esenciales:

### 1. **Backend de Alta Eficiencia:**
Utilizando Spring Boot, se ha desarrollado un servicio backend robusto encargado de gestionar datos de pilotos. Esta parte del sistema interactúa con una base de datos MongoDB para ofrecer una experiencia de almacenamiento optimizada.

### 2. **Interfaz de Usuario Consola:**
Aprovechando las capacidades reactivas de Spring Webflux, se implementa una interfaz de usuario en consola que facilita la gestión de datos de pilotos de una manera dinámica y eficiente.

## Funcionalidades Destacadas
- **Backend Versátil:** Manejo completo de operaciones CRUD para datos de pilotos.
- **Cliente Reactivo:** Uso de WebClient para interacciones asincrónicas y eficientes.
- **Persistencia con MongoDB:** Datos almacenados de forma segura y eficiente.

## Requerimientos Técnicos
- Java JDK 17+.
- Maven para la administración del proyecto.
- MongoDB operativo localmente o en red.

## Implementación y Uso
- **Configuración del Backend:** Define la cadena de conexión de MongoDB en `application.properties`. Accede a la API en `http://localhost:8080/`.
- **Uso del Cliente en Consola:** Explora las funcionalidades del cliente, incluyendo visualización, creación, actualización y eliminación de pilotos.

## Tecnologías Implementadas
- Spring Boot
- Spring Data MongoDB
- Spring Webflux
- Maven
- Java

