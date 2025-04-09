# RoomiesU_Spring
Este repositorio contiene un proyecto desarrollado para la materia de Entornos de Programación. El objetivo es crear un sistema de gestión de pensiones que facilite la comunicación entre arrendatarios y arrendadores.

Tecnologías utilizadas
- Spring Boot, Spring Boot Security 6+, JWT
- JavaScript (JS genérico), HTML, CSS

Como se menciona anteriormente, el proyecto está desarrollado en Java utilizando el framework Spring Boot para el backend y JavaScript junto con HTML y CSS para el frontend, y sigue una arquitectura en capas que separa responsabilidades para facilitar el mantenimiento, escalabilidad y seguridad del sistema. A continuación, se describe la estructura y la función de cada carpeta principal.

## Backend 
### Estructura
```bash
├── config
├── controller
│   └── request
├── model
├── repository
├── security
│   ├── filters
│   ├── jwt
├── service
```

### Descripción de paquetes

#### Config 
Contiene configuraciones globales, como:

- `CorsConfig.java`: Configura CORS para permitir solicitudes del frontend.

#### Controller
Controladores REST que exponen los endpoints HTTP:

- `HabitacionController`: Gestión de habitaciones (CRUD).
- `RegistroController`: Maneja el registro de usuarios a nivel público.
- `RolesController`: Endpoints con cierto nivel de acceso deacuerdo al rol.
- `UserController`: Gestión general del usuario (CRUD).

##### controller/request/
Clases DTO para recibir datos en las solicitudes:

- `CreateUsuarioDTO`
- `UpdateUsuarioDTO`

#### Model
Contiene las clases que representan las entidades del sistema (usuario, habitación, rol, etc.).

#### Repository
Interfaces para acceder a la base de datos con Spring Data JPA.

#### Security
Módulo de seguridad que maneja autenticación y autorización:

- filters
  - `JwtAuthenticationFilter`: Filtra solicitudes para autenticar mediante JWT.
  - `JwtAutorizacionFilter`: Verifica permisos basados en roles.
- jwt
  - `JwtUtils`: Generación y validación de tokens JWT.
- SecurityConfig: Define reglas de seguridad, filtros y accesos permitidos.

#### Service
Contiene la lógica de negocio:

- `HabitacionService`: Lógica para habitaciones.
- `IUsuarioService`: Interfaz del servicio de usuario.
- `UsuarioService`: Lógica para usuarios.
- `UserDetailsServiceImpl`: Carga detalles del usuario autenticado para Spring Security.
