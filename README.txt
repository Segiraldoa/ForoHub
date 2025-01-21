# ForoHub

ForoHub es una API Rest desarrollada con Spring Boot que permite la gestión de tópicos, usuarios y cursos, implementando autenticación basada en JWT. Este proyecto incluye funcionalidades para registrar, listar, actualizar y eliminar tópicos, además de manejar usuarios y sus roles.

---

## Características Principales

- **Gestión de Tópicos**: Crear, listar, actualizar y eliminar tópicos.
- **Autenticación**: Sistema de autenticación con JWT.
- **Base de Datos**: Gestión de entidades Usuario, Curso, Tópico y Respuesta.
- **Validación**: Validaciones para campos obligatorios y manejo de excepciones global.
- **Pruebas Unitarias**: Implementación de pruebas para controladores.

---

## Tecnologías Utilizadas

- **Java**: Versión 23.
- **Spring Boot**: Framework principal del proyecto.
- **MySQL**: Base de datos relacional.
- **JWT**: JSON Web Tokens para autenticación.
- **Flyway**: Gestión de migraciones de base de datos.
- **JUnit y Mockito**: Pruebas unitarias.
- **Maven**: Gestión de dependencias.

---

## Requisitos Previos

1. **Java**: JDK 17 o superior.
2. **Maven**: Configurado para manejar dependencias.
3. **MySQL**: Base de datos en ejecución.
4. **IDE**: Recomendado IntelliJ IDEA.

---

## Instalación y Configuración

1. Clonar este repositorio:

   ```bash
   git clone https://github.com/tu-usuario/foro-hub.git
   ```

2. Configurar la base de datos en el archivo `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
   spring.datasource.username=TU_USUARIO
   spring.datasource.password=TU_CONTRASEÑA
   spring.jpa.hibernate.ddl-auto=update
   jwt.secret=clave-segura-de-tu-eleccion
   jwt.expiration=86400000
   ```

3. Ejecutar migraciones con Flyway:

   ```bash
   mvn flyway:migrate
   ```

4. Compilar y ejecutar la aplicación:

   ```bash
   mvn spring-boot:run
   ```

5. Acceder a Swagger UI para probar la API:

   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## Endpoints Principales

### Autenticación

- **POST /login**: Inicia sesión y devuelve un token JWT.

### Tópicos

- **GET /topicos**: Lista todos los tópicos.
- **GET /topicos/{id}**: Detalle de un tópico específico.
- **POST /topicos**: Crea un nuevo tópico.
- **PUT /topicos/{id}**: Actualiza un tópico existente.
- **DELETE /topicos/{id}**: Elimina un tópico.

### Usuarios

- **GET /usuarios**: Lista todos los usuarios (futuro desarrollo).

---

## Estructura del Proyecto

```plaintext
src
├── main
│   ├── java
│   │   └── com.forohub.sebas.giraldo_2
│   │       ├── config
│   │       ├── controlador
│   │       ├── dto
│   │       ├── excepciones
│   │       ├── filtro
│   │       ├── modelo
│   │       ├── repositorio
│   │       └── servicio
│   └── resources
│       ├── application.properties
│       └── db/migration
└── test
    └── java
        └── com.forohub.sebas.giraldo_2
```

---

## Pruebas

- Ejecutar las pruebas unitarias:

  ```bash
  mvn test
  ```

---

## Autores

- **Juan Sebastian Duque Arango**

---

**Estado actual**: Todas las funcionalidades del backend están listas y operativas. El proyecto está preparado para integrarse con un frontend o para pruebas adicionales.

---

## Contribuciones

¡Contribuciones son bienvenidas! Por favor, abre un issue o un pull request para sugerir mejoras o reportar problemas.
