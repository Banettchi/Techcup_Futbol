# 📄 Planeación del Sistema

## Desglose de trabajo: Épicas, Historias de Usuario y Tareas

La implementación de los requerimientos identificados de TECHCUP FÚTBOL se desglosa de la siguiente manera:

---

## ÉPICAS

### Épica 1

| Campo | Descripción |
|------|-------------|
| **ID** | EP-01 |
| **Título** | Gestión de Usuarios y Autenticación |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica para garantizar que solo usuarios autorizados accedan al sistema, con los permisos correctos según su rol. Sin un sistema de registro y autenticación robusto, no es posible controlar quién puede crear equipos, aprobar pagos o registrar resultados. |
| **Stakeholder** | Administrador, Estudiante, Graduado, Profesor, Personal Administrativo, Familiar |

---

### Épica 2

| Campo | Descripción |
|------|-------------|
| **ID** | EP-02 |
| **Título** | Gestión del Torneo |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica para centralizar la creación, configuración y administración del torneo. Actualmente este proceso es manual y genera desorden; esta épica permite al organizador controlar todas las etapas del torneo desde un solo lugar. |
| **Stakeholder** | Organizador |

---

### Épica 3

| Campo | Descripción |
|------|-------------|
| **ID** | EP-03 |
| **Título** | Gestión de Equipos y Jugadores |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica para facilitar la conformación de equipos de manera organizada, validando las reglas del torneo y permitiendo a los capitanes invitar jugadores disponibles. Sin esta épica, los equipos seguirían completándose de forma tardía y sin control. |
| **Stakeholder** | Capitán, Jugador (Estudiante, Graduado, Profesor, Personal Administrativo, Familiar) |

---

### Épica 4

| Campo | Descripción |
|------|-------------|
| **ID** | EP-04 |
| **Título** | Inscripción y Pagos |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica para reemplazar la verificación manual de pagos por un proceso digital de carga y aprobación de comprobantes, dando trazabilidad al estado de inscripción de cada equipo. |
| **Stakeholder** | Capitán, Organizador |

---

### Épica 5

| Campo | Descripción |
|------|-------------|
| **ID** | EP-05 |
| **Título** | Registro de Partidos y Resultados |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica para eliminar la actualización manual de resultados y tabla de posiciones. Permite al organizador registrar los datos de cada partido y que el sistema calcule automáticamente las estadísticas. |
| **Stakeholder** | Organizador, Árbitro |

---

### Épica 6

| Campo | Descripción |
|------|-------------|
| **ID** | EP-06 |
| **Título** | Consulta e Información Pública del Torneo |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica para centralizar la información del torneo (calendario, tabla de posiciones, estadísticas, llaves eliminatorias y alineaciones) en un solo lugar accesible para todos los usuarios, eliminando la dispersión de información. |
| **Stakeholder** | Estudiante, Graduado, Profesor, Personal Administrativo, Familiar, Capitán, Árbitro |

---

## HISTORIAS DE USUARIO

## Épica 1:
### HU-01

| Campo | Descripción |
|------|-------------|
| **ID** | HU-01 |
| **Épica asociada** | EP-01 |
| **Título** | Registro de usuario con rol |
| **Descripción** | Como usuario nuevo quiero registrarme en la plataforma ingresando mis datos personales y seleccionando mi rol para poder acceder a las funcionalidades que me corresponden. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

### HU-02

| Campo | Descripción |
|------|-------------|
| **ID** | HU-02 |
| **Épica asociada** | EP-01 |
| **Título** | Autenticación de usuario |
| **Descripción** | Como usuario registrado quiero iniciar sesión con mi correo y contraseña para acceder al sistema con los permisos correspondientes a mi rol. |
| **Prioridad** | Alta |
| **Estimación** | 3 puntos de historia |

### HU-03

| Campo | Descripción |
|------|-------------|
| **ID** | HU-03 |
| **Épica asociada** | EP-01 |
| **Título** | Creación de perfil deportivo |
| **Descripción** | Como jugador quiero crear mi perfil deportivo indicando mis posiciones de juego, número dorsal y foto para que los capitanes puedan encontrarme y evaluarme. |
| **Prioridad** | Media |
| **Estimación** | 3 puntos de historia |

## Épica 2:
### HU-04

| Campo | Descripción |
|------|-------------|
| **ID** | HU-04 |
| **Épica asociada** | EP-02 |
| **Título** | Creación de torneo |
| **Descripción** | Como organizador quiero crear un torneo definiendo fechas, número de equipos y costo por equipo para dar inicio al proceso de inscripción. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

### HU-05

| Campo | Descripción |
|------|-------------|
| **ID** | HU-05 |
| **Épica asociada** | EP-02 |
| **Título** | Activar torneo |
| **Descripción** | Como organizador quiero activar el torneo para que los equipos puedan inscribirse y el proceso de competencia dé inicio formalmente. |
| **Prioridad** | Alta |
| **Estimación** | 2 puntos de historia |

### HU-06

| Campo | Descripción |
|------|-------------|
| **ID** | HU-06 |
| **Épica asociada** | EP-02 |
| **Título** | Configuración del torneo |
| **Descripción** | Como organizador quiero configurar el reglamento, fechas importantes, horarios de partidos, canchas y sanciones del torneo para que toda la información esté centralizada y disponible para los participantes. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

## Épica 3:
### HU-07

| Campo | Descripción |
|------|-------------|
| **ID** | HU-07 |
| **Épica asociada** | EP-03 |
| **Título** | Creación de equipo |
| **Descripción** | Como capitán quiero crear un equipo asignándole nombre, escudo y colores del uniforme para participar en el torneo. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

### HU-08

| Campo | Descripción |
|------|-------------|
| **ID** | HU-08 |
| **Épica asociada** | EP-03 |
| **Título** | Invitación y búsqueda de jugadores |
| **Descripción** | Como capitán quiero buscar jugadores disponibles por posición, semestre, edad, género o nombre y enviarles invitaciones para conformar mi equipo. |
| **Prioridad** | Alta |
| **Estimación** | 8 puntos de historia |

### HU-09

| Campo | Descripción |
|------|-------------|
| **ID** | HU-09 |
| **Épica asociada** | EP-03 |
| **Título** | Aceptar o rechazar invitación |
| **Descripción** | Como jugador quiero recibir y responder invitaciones de equipos para decidir si quiero participar en un equipo determinado. |
| **Prioridad** | Alta |
| **Estimación** | 3 puntos de historia |

### HU-10

| Campo | Descripción |
|------|-------------|
| **ID** | HU-10 |
| **Épica asociada** | EP-03 |
| **Título** | Validación de reglas del equipo |
| **Descripción** | Como sistema quiero validar automáticamente que cada equipo cumpla las reglas de composición (mínimo 7, máximo 12 jugadores, sin duplicados, composición por programa) para garantizar la integridad del torneo. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

### HU-11

| Campo | Descripción |
|------|-------------|
| **ID** | HU-11 |
| **Épica asociada** | EP-03 |
| **Título** | Gestión de alineaciones |
| **Descripción** | Como capitán quiero definir la alineación de mi equipo antes de cada partido, seleccionando titulares, reservas y formación, para organizar estratégicamente mis jugadores. |
| **Prioridad** | Media |
| **Estimación** | 8 puntos de historia |

## Épica 4:
### HU-12

| Campo | Descripción |
|------|-------------|
| **ID** | HU-12 |
| **Épica asociada** | EP-04 |
| **Título** | Registro de comprobante de pago |
| **Descripción** | Como capitán quiero subir el comprobante de pago de inscripción de mi equipo para que el organizador pueda revisarlo y aprobar nuestra participación en el torneo. |
| **Prioridad** | Alta |
| **Estimación** | 3 puntos de historia |

### HU-13

| Campo | Descripción |
|------|-------------|
| **ID** | HU-13 |
| **Épica asociada** | EP-04 |
| **Título** | Aprobación o rechazo de pago |
| **Descripción** | Como organizador quiero revisar los comprobantes de pago y aprobarlos o rechazarlos para controlar qué equipos quedan inscritos oficialmente en el torneo. |
| **Prioridad** | Alta |
| **Estimación** | 3 puntos de historia |

## Épica 5:
### HU-14

| Campo | Descripción |
|------|-------------|
| **ID** | HU-14 |
| **Épica asociada** | EP-05 |
| **Título** | Registro de resultados de partido |
| **Descripción** | Como organizador quiero registrar el marcador, goleadores y tarjetas de cada partido para mantener actualizada la información del torneo. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

### HU-15

| Campo | Descripción |
|------|-------------|
| **ID** | HU-15 |
| **Épica asociada** | EP-05 |
| **Título** | Consulta de partido por árbitro |
| **Descripción** | Como árbitro quiero consultar la fecha, hora, cancha y equipos del partido que me corresponde arbitrar para prepararme adecuadamente. |
| **Prioridad** | Media |
| **Estimación** | 2 puntos de historia |

## Épica 6:
### HU-16

| Campo | Descripción |
|------|-------------|
| **ID** | HU-16 |
| **Épica asociada** | EP-06 |
| **Título** | Consulta de tabla de posiciones y estadísticas |
| **Descripción** | Como usuario quiero consultar la tabla de posiciones actualizada y las estadísticas del torneo (goleadores, historial de partidos, resultados por equipo) para seguir el desarrollo de la competencia. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

### HU-17

| Campo | Descripción |
|------|-------------|
| **ID** | HU-17 |
| **Épica asociada** | EP-06 |
| **Título** | Generación y consulta de llaves eliminatorias |
| **Descripción** | Como usuario quiero visualizar las llaves eliminatorias generadas automáticamente por el sistema para conocer los cruces de cuartos de final, semifinal y final. |
| **Prioridad** | Alta |
| **Estimación** | 8 puntos de historia |

### HU-18

| Campo | Descripción |
|------|-------------|
| **ID** | HU-18 |
| **Épica asociada** | EP-06 |
| **Título** | Consulta de alineación del equipo rival |
| **Descripción** | Como capitán o jugador quiero consultar la alineación del equipo rival antes del partido para conocer su formación y planificar mi estrategia. |
| **Prioridad** | Baja |
| **Estimación** | 2 puntos de historia |

---

## TAREAS

### TR-01

| Campo | Descripción |
|------|-------------|
| **ID** | TR-01 |
| **Título** | Diseñar modelo de base de datos para usuarios y roles |
| **ID de la Historia de Usuario asociada** | HU-01 |
| **Descripción** | Crear el esquema de tablas en PostgreSQL para almacenar usuarios, roles y credenciales, incluyendo las restricciones de integridad necesarias. |
| **Tareas requisito** | Ninguna |

### TR-02

| Campo | Descripción |
|------|-------------|
| **ID** | TR-02 |
| **Título** | Implementar endpoint de registro de usuario (backend) |
| **ID de la Historia de Usuario asociada** | HU-01 |
| **Descripción** | Desarrollar el controlador, servicio y repositorio en Spring Boot para registrar un nuevo usuario con su rol asignado. |
| **Tareas requisito** | TR-01 |

### TR-03

| Campo | Descripción |
|------|-------------|
| **ID** | TR-03 |
| **Título** | Implementar formulario de registro (frontend) |
| **ID de la Historia de Usuario asociada** | HU-01 |
| **Descripción** | Desarrollar el componente React con TypeScript para el formulario de registro, con validaciones de campos y selección de rol. |
| **Tareas requisito** | TR-02 |

### TR-04

| Campo | Descripción |
|------|-------------|
| **ID** | TR-04 |
| **Título** | Implementar autenticación con JWT (backend) |
| **ID de la Historia de Usuario asociada** | HU-02 |
| **Descripción** | Desarrollar el mecanismo de autenticación basado en correo y contraseña con generación de token JWT y control de permisos por rol. |
| **Tareas requisito** | TR-01 |

### TR-05

| Campo | Descripción |
|------|-------------|
| **ID** | TR-05 |
| **Título** | Implementar pantalla de login (frontend) |
| **ID de la Historia de Usuario asociada** | HU-02 |
| **Descripción** | Desarrollar el componente React de inicio de sesión con manejo de token y redirección según el rol del usuario autenticado. |
| **Tareas requisito** | TR-04 |

### TR-06

| Campo | Descripción |
|------|-------------|
| **ID** | TR-06 |
| **Título** | Implementar perfil deportivo del jugador (backend) |
| **ID de la Historia de Usuario asociada** | HU-03 |
| **Descripción** | Crear el endpoint y lógica de negocio para guardar y actualizar el perfil deportivo del jugador (posición, dorsal, foto, disponibilidad). |
| **Tareas requisito** | TR-01 |

### TR-07

| Campo | Descripción |
|------|-------------|
| **ID** | TR-07 |
| **Título** | Implementar pantalla de perfil deportivo (frontend) |
| **ID de la Historia de Usuario asociada** | HU-03 |
| **Descripción** | Desarrollar el componente React para que el jugador complete y edite su perfil deportivo, incluyendo carga de foto. |
| **Tareas requisito** | TR-06 |

### TR-08

| Campo | Descripción |
|------|-------------|
| **ID** | TR-08 |
| **Título** | Diseñar modelo de base de datos para torneos |
| **ID de la Historia de Usuario asociada** | HU-04 |
| **Descripción** | Crear el esquema de tablas en PostgreSQL para almacenar torneos con sus estados (Borrador, Activo, En progreso, Finalizado) y campos requeridos. |
| **Tareas requisito** | TR-01 |

### TR-09

| Campo | Descripción |
|------|-------------|
| **ID** | TR-09 |
| **Título** | Implementar endpoint de creación de torneo (backend) |
| **ID de la Historia de Usuario asociada** | HU-04 |
| **Descripción** | Desarrollar el controlador y servicio en Spring Boot para que el organizador pueda crear un torneo con la información básica requerida. |
| **Tareas requisito** | TR-08 |

### TR-10

| Campo | Descripción |
|------|-------------|
| **ID** | TR-10 |
| **Título** | Implementar formulario de creación de torneo (frontend) |
| **ID de la Historia de Usuario asociada** | HU-04 |
| **Descripción** | Desarrollar el componente React para que el organizador ingrese los datos del torneo y lo registre en el sistema. |
| **Tareas requisito** | TR-09 |

### TR-11

| Campo | Descripción |
|------|-------------|
| **ID** | TR-11 |
| **Título** | Implementar endpoint de activación de torneo (backend) |
| **ID de la Historia de Usuario asociada** | HU-05 |
| **Descripción** | Desarrollar el endpoint que cambia el estado del torneo de "Borrador" a "Activo", validando que se cumplan las condiciones previas. |
| **Tareas requisito** | TR-09 |

### TR-12

| Campo | Descripción |
|------|-------------|
| **ID** | TR-12 |
| **Título** | Implementar pantalla de configuración del torneo (frontend) |
| **ID de la Historia de Usuario asociada** | HU-06 |
| **Descripción** | Desarrollar el componente React para que el organizador configure reglamento, fechas, horarios, canchas y sanciones del torneo. |
| **Tareas requisito** | TR-10 |

### TR-13

| Campo | Descripción |
|------|-------------|
| **ID** | TR-13 |
| **Título** | Diseñar modelo de base de datos para equipos y jugadores |
| **ID de la Historia de Usuario asociada** | HU-07 |
| **Descripción** | Crear el esquema de tablas en PostgreSQL para equipos, integrantes y la relación equipo-jugador, incluyendo restricciones de unicidad. |
| **Tareas requisito** | TR-01 |

### TR-14

| Campo | Descripción |
|------|-------------|
| **ID** | TR-14 |
| **Título** | Implementar endpoint de creación de equipo (backend) |
| **ID de la Historia de Usuario asociada** | HU-07 |
| **Descripción** | Desarrollar el controlador y servicio en Spring Boot para que el capitán pueda crear un equipo con nombre, escudo y colores. |
| **Tareas requisito** | TR-13 |

### TR-15

| Campo | Descripción |
|------|-------------|
| **ID** | TR-15 |
| **Título** | Implementar formulario de creación de equipo (frontend) |
| **ID de la Historia de Usuario asociada** | HU-07 |
| **Descripción** | Desarrollar el componente React para la creación del equipo, incluyendo carga de escudo y selección de colores del uniforme. |
| **Tareas requisito** | TR-14 |

### TR-16

| Campo | Descripción |
|------|-------------|
| **ID** | TR-16 |
| **Título** | Implementar buscador y sistema de invitaciones (backend) |
| **ID de la Historia de Usuario asociada** | HU-08 |
| **Descripción** | Desarrollar los endpoints para buscar jugadores disponibles por filtros (posición, semestre, edad, género, nombre) y enviar invitaciones. |
| **Tareas requisito** | TR-13 |

### TR-17

| Campo | Descripción |
|------|-------------|
| **ID** | TR-17 |
| **Título** | Implementar pantalla de búsqueda e invitación de jugadores (frontend) |
| **ID de la Historia de Usuario asociada** | HU-08 |
| **Descripción** | Desarrollar el componente React con filtros de búsqueda de jugadores disponibles y la opción de enviar invitaciones desde el resultado. |
| **Tareas requisito** | TR-16 |

### TR-18

| Campo | Descripción |
|------|-------------|
| **ID** | TR-18 |
| **Título** | Implementar aceptación/rechazo de invitaciones (backend y frontend) |
| **ID de la Historia de Usuario asociada** | HU-09 |
| **Descripción** | Desarrollar el endpoint y componente React para que el jugador visualice sus invitaciones pendientes y pueda aceptarlas o rechazarlas. |
| **Tareas requisito** | TR-16 |

### TR-19

| Campo | Descripción |
|------|-------------|
| **ID** | TR-19 |
| **Título** | Implementar validaciones de reglas del equipo (backend) |
| **ID de la Historia de Usuario asociada** | HU-10 |
| **Descripción** | Desarrollar la lógica de negocio que valide mínimo/máximo de jugadores, que un jugador no pertenezca a dos equipos y la composición por programa académico. |
| **Tareas requisito** | TR-13 |

### TR-20

| Campo | Descripción |
|------|-------------|
| **ID** | TR-20 |
| **Título** | Implementar gestión de alineaciones (backend) |
| **ID de la Historia de Usuario asociada** | HU-11 |
| **Descripción** | Desarrollar los endpoints para que el capitán defina titulares, reservas y formación del equipo antes de cada partido. |
| **Tareas requisito** | TR-13, TR-14 |

### TR-21

| Campo | Descripción |
|------|-------------|
| **ID** | TR-21 |
| **Título** | Implementar pantalla visual de alineación en cancha (frontend) |
| **ID de la Historia de Usuario asociada** | HU-11 |
| **Descripción** | Desarrollar el componente React que permita al capitán ubicar jugadores visualmente en la cancha y seleccionar la formación táctica. |
| **Tareas requisito** | TR-20 |

### TR-22

| Campo | Descripción |
|------|-------------|
| **ID** | TR-22 |
| **Título** | Implementar carga de comprobante de pago (backend y frontend) |
| **ID de la Historia de Usuario asociada** | HU-12 |
| **Descripción** | Desarrollar el endpoint y componente React para que el capitán suba el comprobante de pago, registrándolo en estado "Pendiente de revisión". |
| **Tareas requisito** | TR-13 |

### TR-23

| Campo | Descripción |
|------|-------------|
| **ID** | TR-23 |
| **Título** | Implementar revisión y aprobación de pagos (backend y frontend) |
| **ID de la Historia de Usuario asociada** | HU-13 |
| **Descripción** | Desarrollar el endpoint y componente React para que el organizador liste los pagos pendientes, visualice el comprobante y los apruebe o rechace. |
| **Tareas requisito** | TR-22 |

### TR-24

| Campo | Descripción |
|------|-------------|
| **ID** | TR-24 |
| **Título** | Diseñar modelo de base de datos para partidos y resultados |
| **ID de la Historia de Usuario asociada** | HU-14 |
| **Descripción** | Crear el esquema de tablas en PostgreSQL para partidos, goleadores, tarjetas y marcadores, relacionados con equipos y torneo. |
| **Tareas requisito** | TR-08, TR-13 |

### TR-25

| Campo | Descripción |
|------|-------------|
| **ID** | TR-25 |
| **Título** | Implementar registro de resultados de partido (backend) |
| **ID de la Historia de Usuario asociada** | HU-14 |
| **Descripción** | Desarrollar el endpoint en Spring Boot para registrar marcador, goleadores, tarjetas amarillas y tarjetas rojas de un partido. |
| **Tareas requisito** | TR-24 |

### TR-26

| Campo | Descripción |
|------|-------------|
| **ID** | TR-26 |
| **Título** | Implementar pantalla de registro de partido (frontend) |
| **ID de la Historia de Usuario asociada** | HU-14 |
| **Descripción** | Desarrollar el componente React para que el organizador ingrese los datos del partido finalizado (marcador, goleadores, tarjetas). |
| **Tareas requisito** | TR-25 |

### TR-27

| Campo | Descripción |
|------|-------------|
| **ID** | TR-27 |
| **Título** | Implementar consulta de partidos para árbitro (backend y frontend) |
| **ID de la Historia de Usuario asociada** | HU-15 |
| **Descripción** | Desarrollar el endpoint y componente React para que el árbitro consulte la fecha, hora, cancha y equipos de los partidos que le corresponden. |
| **Tareas requisito** | TR-24 |

### TR-28

| Campo | Descripción |
|------|-------------|
| **ID** | TR-28 |
| **Título** | Implementar cálculo automático de tabla de posiciones (backend) |
| **ID de la Historia de Usuario asociada** | HU-16 |
| **Descripción** | Desarrollar la lógica en Spring Boot que calcule automáticamente puntos, partidos jugados/ganados/empatados/perdidos, goles a favor, en contra y diferencia de gol tras registrar un resultado. |
| **Tareas requisito** | TR-25 |

### TR-29

| Campo | Descripción |
|------|-------------|
| **ID** | TR-29 |
| **Título** | Implementar pantalla de tabla de posiciones y estadísticas (frontend) |
| **ID de la Historia de Usuario asociada** | HU-16 |
| **Descripción** | Desarrollar el componente React para mostrar la tabla de posiciones, máximos goleadores, historial de partidos y resultados por equipo. |
| **Tareas requisito** | TR-28 |

### TR-30

| Campo | Descripción |
|------|-------------|
| **ID** | TR-30 |
| **Título** | Implementar generación automática de llaves eliminatorias (backend) |
| **ID de la Historia de Usuario asociada** | HU-17 |
| **Descripción** | Desarrollar la lógica en Spring Boot que genere automáticamente los cruces eliminatorios (cuartos, semifinal, final) al iniciarse la fase eliminatoria. |
| **Tareas requisito** | TR-24, TR-28 |

### TR-31

| Campo | Descripción |
|------|-------------|
| **ID** | TR-31 |
| **Título** | Implementar pantalla de llaves eliminatorias (frontend) |
| **ID de la Historia de Usuario asociada** | HU-17 |
| **Descripción** | Desarrollar el componente React para visualizar el bracket de llaves eliminatorias actualizado con los resultados de cada fase. |
| **Tareas requisito** | TR-30 |

### TR-32

| Campo | Descripción |
|------|-------------|
| **ID** | TR-32 |
| **Título** | Implementar consulta de alineación del equipo rival (frontend) |
| **ID de la Historia de Usuario asociada** | HU-18 |
| **Descripción** | Desarrollar el componente React que permita a capitanes y jugadores visualizar la alineación del equipo contrario antes de un partido. |
| **Tareas requisito** | TR-21 |
