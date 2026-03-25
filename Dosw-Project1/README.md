# TECHCUP FÚTBOL — Sistema de Gestión del Torneo

Plataforma web para gestionar de forma centralizada el torneo semestral de fútbol de los programas de Ingeniería de Sistemas, Inteligencia Artificial, Ciberseguridad y Estadística de la Escuela Colombiana de Ingeniería Julio Garavito.

---

## Estructura del Proyecto

```
Dosw-Project1/
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── com/example/
    │           ├── controller/
    │           ├── service/
    │           ├── repository/
    │           ├── entity/
    │           └── App.java
    └── test/
        └── java/
            └── com/example/
                └── AppTest.java
└── docs/
    ├── uml/
    ├── images/
    ├── requirements/
    └── planning/
```

---

## Requisitos

- Java 21
- Apache Maven 3.9+
- PostgreSQL 15+ (producción)
- Docker (opcional, para levantar la base de datos)

---

## Ejecución

```bash
# Compilar el proyecto
mvn compile

# Ejecutar pruebas
mvn test

# Empaquetar
mvn package
```

Levantar PostgreSQL con Docker:

```bash
docker run --name techcup-db \
  -e POSTGRES_DB=techcupdb \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres
```

---

## Entidades del Dominio

Las siguientes entidades fueron seleccionadas porque cubren las tres funcionalidades base que debe tener el sistema: autenticación, gestión de usuarios y gestión del torneo. Cada una representa un concepto real del negocio y tiene información propia que necesita ser almacenada en la base de datos.

---

### Rol

**Tabla:** `roles`

Representa los distintos tipos de usuario que existen en el sistema. Cada rol define qué puede ver y hacer una persona dentro de la plataforma. Los roles contemplados son: Estudiante, Graduado, Profesor, Personal Administrativo, Familiar, Capitán, Organizador, Árbitro y Administrador.

Esta entidad es necesaria porque el sistema tiene comportamientos completamente distintos según quién esté usando la plataforma: un Organizador puede crear torneos y aprobar pagos, mientras que un Estudiante solo puede consultar información y unirse a equipos.

**Atributos principales:** identificador único, nombre del rol.

---

### Usuario

**Tabla:** `usuarios`

Representa a cualquier persona registrada en el sistema, sin importar su tipo. Almacena los datos personales necesarios para identificarla y autenticarla, así como el rol que determina sus permisos.

Esta entidad es el centro del sistema de autenticación: cuando alguien inicia sesión, el sistema busca su correo en esta tabla, valida su contraseña y carga el rol asociado para saber a qué secciones puede acceder.

**Atributos principales:** identificador único, nombre completo, correo electrónico, contraseña (cifrada), estado (activo/inactivo) y rol asignado.

---

### Torneo

**Tabla:** `torneos`

Representa el torneo de fútbol en sí. Almacena toda la información que el Organizador define al momento de crearlo, y lleva el seguimiento de su estado a lo largo del semestre.

Esta entidad es el núcleo del sistema: sin un torneo activo, no es posible inscribir equipos, programar partidos ni registrar resultados. Su estado cambia a lo largo del proceso: inicia como *En proceso* cuando se está configurando, pasa a *Activo* cuando se abren las inscripciones, luego a *En progreso* durante la competencia, y finalmente a *Finalizado*.

**Atributos principales:** identificador único, fecha de inicio, fecha de fin, costo de inscripción por equipo, número máximo de equipos, estado actual y organizador responsable.

---

### Relaciones entre entidades

- **Rol → Usuario:** un rol puede estar asignado a muchos usuarios, pero cada usuario tiene un único rol. Por ejemplo, puede haber decenas de estudiantes registrados compartiendo el rol "Estudiante", pero un usuario no puede tener dos roles al mismo tiempo.

- **Usuario → Torneo:** un usuario con rol Organizador puede crear y gestionar varios torneos a lo largo del tiempo (uno por semestre, por ejemplo), pero cada torneo pertenece a un único organizador responsable. No puede existir un torneo sin un organizador asignado.

- **Rol → Torneo:** el rol de un usuario determina qué operaciones puede realizar sobre un torneo. Solo un usuario con rol Organizador puede cambiar el estado del torneo (por ejemplo, de *En proceso* a *Activo*). Esta restricción conecta las tres entidades: el sistema consulta el rol del usuario antes de permitir cualquier acción sobre el torneo.

- **Usuario → Torneo** el sistema debe registrar qué usuario realizó acciones relevantes sobre el torneo, como su creación o cambio de estado. Esto implica que el torneo guarda una referencia al usuario responsable no solo como creador, sino también como actor de cada modificación importante.

---

## Tecnologías

| Capa | Tecnología |
|------|------------|
| Backend | Spring Boot 3, Java 21 |
| Persistencia | Spring Data JPA, PostgreSQL |
| Pruebas | JUnit 5, H2 (en memoria) |
| Build | Apache Maven |