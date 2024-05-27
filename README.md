## Caso de Estudio Genérico: Diseño y Creación de un Backend con Capa de Seguridad y Pruebas Unitarias

# Sistema de Gestión de Biblioteca

# Requisitos Técnicos:

- Lenguaje de programación: Java
- Framework: Spring Boot
- Bases de datos: MongoDB y una base de datos relacional (MySQL o PostgreSQL)
- Seguridad: JWT (JSON Web Tokens)
- Principio de Inversión de Control


Tareas a Realizar:

# Configuración Inicial:

- Crea un proyecto Spring Boot.
- Configura las dependencias para MongoDB y una base de datos relacional.

# Modelo de Datos:

- Define clases del modelo de datos para tu caso específico, como entidades y documentos.
# Conexión con Bases de Datos:

- Implementa servicios para persistencia con MongoDB y la base de datos relacional.
- Implementa los repositorios y las clases necesarias para cada tipo de base de datos.

# Controlador REST:

- Crea un controlador REST con endpoints para operaciones CRUD y otras funcionalidades específicas de tu caso.

# Servicio de Negocio:

- Implementa la lógica de negocio.

# Principio de Inversión de Control:

- Utiliza la inversión de control para cambiar entre bases de datos.
# Seguridad:

- Implementa seguridad utilizando JWT.
- Configura la autenticación y autorización en tus endpoints.

# Pruebas Unitarias:

- Escribe pruebas unitarias para los componentes clave de tu aplicación (modelos, repositorios, servicios, controladores).
- Utiliza frameworks de pruebas como JUnit y Mockito.

# Criterios de Aceptación:
- Cumplimiento del modelo de madurez Richardson nivel 2.
- Uso correcto de verbos HTTP y códigos de estado.
- Implementación adecuada de Spring Data.
- Funcionalidad CRUD completa.
- Manejo de excepciones.
- Seguridad con JWT implementada correctamente.
- Cobertura de pruebas unitarias en componentes críticos.

# Instrucciones:
- Diseña y crea un proyecto de Spring Boot que se ajuste al contexto y requisitos dados.
- Sigue las tareas detalladas para desarrollar la solución.
- Asegúrate de que el proyecto funcione correctamente y cumpla con los criterios de aceptación.
- Documenta tu código y comparte tu solución, enfocándote en las clases y componentes principales.
- Atributos de Calidad Esperados:

# Uso de mejores prácticas de codificación.
- Adherencia a los principios SOLID.
- Claro uso de la inyección de dependencias.
- Uso correcto de las anotaciones de Spring Boot.
- Conexión efectiva con capas de persistencia.

