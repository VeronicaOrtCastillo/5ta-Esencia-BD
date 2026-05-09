# Proyecto Spring Boot + MySQL

Proyecto desarrollado con Spring Boot utilizando arquitectura por capas y conexión a base de datos MySQL.

---

# Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Gradle
- IntelliJ IDEA

---

# Arquitectura del proyecto

El proyecto está organizado por capas:

- Controller → Maneja las peticiones HTTP
- Service → Contiene la lógica de negocio
- Repository → Comunicación con la base de datos
- Model → Entidades de la aplicación

---

# Requisitos previos

Antes de ejecutar el proyecto necesitas tener instalado:

- Java 17 o superior
- MySQL Server
- IntelliJ IDEA
- Git

---

# Clonar el proyecto

```bash
git clone https://github.com/VeronicaOrtCastillo/5ta-Esencia-BD.git
```

Entrar a la carpeta:

```bash
cd TU_REPOSITORIO
```

---

# Configuración de la base de datos

## 1. Crear la base de datos

Dentro de la carpeta:

```text
BD-MySQL
```

se encuentran los archivos:

- `create.sql`
- `insert.sql`

## 2. Ejecutar create.sql

Este archivo crea:

- la base de datos
- tablas
- relaciones

Puedes ejecutarlo desde:

- MySQL Workbench

---

## 3. Ejecutar insert.sql

Este archivo inserta registros iniciales en la base de datos.

---

# Configurar credenciales de MySQL

Ir a:

```text
src > resources > application.properties
```
Configuración del application.properties

Aqui deberas cambiar la ruta de accceso a la base de datos ademas del usuario y contraseña que estes utilizando en MySQL

```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/quintaesencia
spring.datasource.username=USER
spring.datasource.password=PASSWORD
```

---

# Ejecutar el proyecto

## Desde IntelliJ

1. Abrir el proyecto
2. Esperar a que Gradle descargue dependencias
3. Ejecutar la clase principal:

```text
Application.java
```

---

# Notas importantes
- Se recomienda usar IntelliJ IDEA para evitar problemas de compatibilidad.
- Si aparecen errores de Gradle relacionados con OneDrive, mover el proyecto fuera de OneDrive.

---

# Autor

Team 5ta_Esencia