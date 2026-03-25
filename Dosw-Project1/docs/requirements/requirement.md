# 📄 Requerimientos del Sistema

## 1. Lista general de requerimientos

El sistema de TECHCUP FÚTBOL tiene los siguientes requerimientos (descripción a alto nivel):

### 1.1 Requerimientos funcionales

El sistema de TECHCUP FÚTBOL debe tener la capacidad de:

1. Permitir el registro de usuarios con diferentes roles (Estudiante, Graduado, Profesor, Personal Administrativo, Familiar, Capitán, Organizador, Árbitro, Administrador).
2. Autenticar usuarios mediante correo y contraseña, otorgando permisos según su rol.
3. Crear y gestionar torneos (definir fechas, número de equipos, costo, estados y configuración general).
4. Activar un torneo para habilitar la inscripción de equipos.
5. Crear y administrar equipos (nombre, escudo, colores, invitación de jugadores).
6. Invitar jugadores disponibles y permitirles aceptar o rechazar la invitación.
7. Validar las reglas de conformación de equipos (mínimo 7, máximo 12 jugadores, sin duplicados, composición por programa).
8. Registrar el comprobante de pago de inscripción por parte del capitán.
9. Aprobar o rechazar el comprobante de pago por parte del organizador.
10. Configurar el torneo (reglamento, fechas importantes, horarios, canchas y sanciones).
11. Registrar los resultados de cada partido (marcador, goleadores, tarjetas).
12. Calcular automáticamente la tabla de posiciones y estadísticas por equipo.
13. Generar automáticamente las llaves eliminatorias (cuartos de final, semifinal y final).
14. Permitir la consulta de información del torneo (calendario, tabla de posiciones, resultados y estadísticas).

### 1.2 Requerimientos no funcionales

El sistema de TECHCUP FÚTBOL debe tener:

1. Registro y auditoría de acciones relevantes (creación de torneo, aprobación de pagos, registro de resultados) para garantizar trazabilidad.
2. Arquitectura backend por capas (controladores, lógica de negocio y acceso a datos) implementada con Spring Boot y API REST, con frontend en React + TypeScript y base de datos PostgreSQL.
3. Integridad de datos que evite registros duplicados y relaciones inválidas (por ejemplo, un jugador no puede pertenecer a dos equipos).
4. Rendimiento con tiempo máximo de respuesta de 3 segundos para operaciones comunes (consultar tabla, calendario, listar equipos).
5. Disponibilidad mínima del 95% de uptime durante el periodo activo del torneo (semanas 6 a 12 del semestre).
6. Compatibilidad con los navegadores Chrome, Edge y Firefox en sus versiones actuales.
7. Identidad visual consistente en toda la interfaz: paleta de colores (Verde Oscuro, Verde Medio, Dorado, Blanco, Gris Oscuro), tipografía Montserrat/Inter y uso correcto del logotipo TECHCUP FÚTBOL.

---

## 2. Diagramas de caso de uso

### 2.1 Requerimiento Funcional 1

| Campo | Descripción |
|------|-------------|
| **ID** | RF-01 |
| **Nombre del requerimiento** | Registro de Usuario |
| **Descripción** | El sistema debe permitir que un usuario se registre ingresando su información personal y seleccionando su tipo de rol. Los usuarios de la Escuela (estudiantes, graduados, profesores, personal administrativo) se registran con correo institucional; los familiares, con correo Gmail. |
| **Precondiciones** | El usuario no debe estar previamente registrado en el sistema. |
| **Actor** | Estudiante, Graduado, Profesor, Personal Administrativo, Familiar |
| **Flujo principal** | 1. El actor ingresa al formulario de registro.<br>2. El actor diligencia su información personal (nombre, correo, contraseña) y selecciona su tipo de rol.<br>3. El sistema valida que el correo no esté registrado previamente.<br>4. El sistema almacena el usuario con su rol asignado y estado activo. |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | El usuario queda almacenado en la base de datos con rol asignado y estado activo. |

---

### 2.2 Requerimiento Funcional 2

| Campo | Descripción |
|------|-------------|
| **ID** | RF-02 |
| **Nombre del requerimiento** | Autenticación de Usuario |
| **Descripción** | El sistema debe permitir a los usuarios autenticarse mediante correo y contraseña para acceder a las funcionalidades correspondientes a su rol. |
| **Precondiciones** | El usuario debe estar previamente registrado en el sistema. |
| **Actor** | Estudiante, Graduado, Profesor, Personal Administrativo, Familiar, Capitán, Organizador, Árbitro, Administrador |
| **Flujo principal** | 1. El actor ingresa su correo electrónico y contraseña.<br>2. El sistema valida las credenciales contra la base de datos.<br>3. El sistema concede acceso y carga la interfaz correspondiente al rol del usuario. |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | El usuario accede al sistema con los permisos correspondientes a su rol. |

---

### 2.3 Requerimiento Funcional 3

| Campo | Descripción |
|------|-------------|
| **ID** | RF-03 |
| **Nombre del requerimiento** | Creación de Torneo |
| **Descripción** | El sistema debe permitir al Organizador crear un torneo mediante un formulario con validaciones, mostrando un resumen en tiempo real de la información ingresada antes de confirmar el registro. |
| **Precondiciones** | El usuario debe haber iniciado sesión con rol de Organizador. |
| **Actor** | Organizador |
| **Flujo principal** | 1. El Organizador inicia sesión seleccionando el rol "Organizador" e ingresando su correo institucional y contraseña en la pantalla de inicio.<br>2. El sistema redirige al Organizador al "Dashboard Organizador", donde puede visualizar la lista "Mis Torneos" con los torneos finalizados, en progreso o activos, junto con el botón "+ Crear Torneo".<br>3. El Organizador hace clic en el botón "+ Crear Torneo".<br>4. El sistema abre el formulario "Crear Torneo" con la sección "Información General", donde el Organizador ingresa la "Fecha de Inicio" y la "Fecha de Fin". El sistema valida que la Fecha de Inicio sea mayor o igual a la fecha actual y que la Fecha de Fin sea mayor a la Fecha de Inicio.<br>5. El Organizador completa la sección "Configuración" ingresando: el "Costo por Equipos" (debe ser un valor numérico mayor a 1.000 pesos), el "Máx. Equipos" (cantidad máxima de equipos permitidos) y el "Jugadores / Equipo" (cantidad máxima de jugadores por equipo).<br>6. El sistema actualiza automáticamente la sección "Resumen" mostrando: en "Información General" la Fecha de inicio, Fecha de fin y el Estado inicial fijo "En proceso"; en "Configuración" el Costo por equipos, Equipos máx. y Jugadores / equipo.<br>7. El Organizador hace clic en el botón "+ CREAR TORNEO".<br>8. El sistema registra el torneo con estado "En proceso" y redirige al "Dashboard Organizador", donde el nuevo torneo aparece en la lista "Mis Torneos". |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | El torneo queda registrado en el sistema con estado "En proceso" y visible en la lista "Mis Torneos" del Dashboard Organizador. |
---

### 2.4 Requerimiento Funcional 4

| Campo | Descripción |
|------|-------------|
| **ID** | RF-04 |
| **Nombre del requerimiento** | Activar Torneo |
| **Descripción** | El sistema debe permitir al organizador cambiar el estado del torneo a "Activo" para habilitar la inscripción de equipos. |
| **Precondiciones** | El torneo debe estar en estado "Borrador". |
| **Actor** | Organizador |
| **Flujo principal** | 1. El organizador accede al torneo en estado "Borrador".<br>2. El organizador selecciona la opción "Activar Torneo".<br>3. El sistema cambia el estado del torneo a "Activo".<br>4. El sistema habilita la inscripción de equipos. |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | El torneo queda disponible para la inscripción de equipos. |

---

### 2.5 Requerimiento Funcional 5

| Campo | Descripción |
|------|-------------|
| **ID** | RF-05 |
| **Nombre del requerimiento** | Creación de Equipo |
| **Descripción** | El sistema debe permitir al capitán crear un equipo asignándole nombre, escudo y colores del uniforme. |
| **Precondiciones** | El torneo debe estar activo y el usuario debe tener rol de Capitán. |
| **Actor** | Capitán |
| **Flujo principal** | 1. El capitán accede a la opción "Crear Equipo".<br>2. El capitán ingresa el nombre del equipo, sube el escudo y define los colores del uniforme.<br>3. El sistema valida la información.<br>4. El sistema registra el equipo en estado pendiente de inscripción. |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | El equipo queda registrado en estado pendiente de inscripción. |

---

### 2.6 Requerimiento Funcional 6

| Campo | Descripción |
|------|-------------|
| **ID** | RF-06 |
| **Nombre del requerimiento** | Invitación de Jugadores |
| **Descripción** | El sistema debe permitir al capitán invitar jugadores disponibles para conformar su equipo. |
| **Precondiciones** | El equipo debe existir y no superar el máximo permitido de 12 jugadores. |
| **Actor** | Capitán, Jugador (Estudiante, Graduado, Profesor, Personal Administrativo, Familiar) |
| **Flujo principal** | 1. El capitán busca jugadores disponibles (por posición, semestre, edad, género, nombre o identificación).<br>2. El capitán envía una invitación al jugador seleccionado.<br>3. El jugador recibe la invitación y decide aceptarla o rechazarla.<br>4. El sistema asocia al jugador al equipo si acepta la invitación. |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | El jugador queda asociado al equipo si acepta la invitación. |

---

### 2.7 Requerimiento Funcional 7

| Campo | Descripción |
|------|-------------|
| **ID** | RF-07 |
| **Nombre del requerimiento** | Validación de Reglas del Equipo |
| **Descripción** | El sistema debe validar que cada equipo tenga mínimo 7 y máximo 12 jugadores, que ningún jugador pertenezca a más de un equipo y que más de la mitad de los miembros sean de los programas de Ingeniería de Sistemas, IA, Ciberseguridad y Estadística. |
| **Precondiciones** | Se intenta agregar un jugador al equipo. |
| **Actor** | Sistema, Capitán |
| **Flujo principal** | 1. El capitán intenta agregar un jugador al equipo.<br>2. El sistema verifica que el equipo no supere el máximo de 12 jugadores.<br>3. El sistema verifica que el jugador no pertenezca ya a otro equipo.<br>4. El sistema verifica que se cumpla la regla de composición por programa académico.<br>5. El sistema permite o bloquea la acción según las reglas establecidas. |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | El sistema permite o bloquea la acción según las reglas establecidas. |

---

### 2.8 Requerimiento Funcional 8

| Campo | Descripción |
|------|-------------|
| **ID** | RF-08 |
| **Nombre del requerimiento** | Registro de Pago |
| **Descripción** | El sistema debe permitir al capitán cargar el comprobante de pago correspondiente a la inscripción del equipo en el torneo. |
| **Precondiciones** | El equipo debe estar conformado y el torneo debe estar activo. |
| **Actor** | Capitán |
| **Flujo principal** | 1. El capitán accede a la sección de pagos de su equipo.<br>2. El capitán sube el comprobante de pago (NEQUI o efectivo).<br>3. El sistema registra el comprobante con estado "Pendiente de revisión". |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | El pago queda registrado en estado "Pendiente de revisión". |

---

### 2.9 Requerimiento Funcional 9

| Campo | Descripción |
|------|-------------|
| **ID** | RF-09 |
| **Nombre del requerimiento** | Aprobación de Pago |
| **Descripción** | El sistema debe permitir al organizador revisar y aprobar o rechazar el comprobante de pago subido por el capitán. |
| **Precondiciones** | El pago debe estar en estado "Pendiente". |
| **Actor** | Organizador |
| **Flujo principal** | 1. El organizador accede a la lista de pagos pendientes.<br>2. El organizador revisa el comprobante de pago del equipo.<br>3. El organizador aprueba o rechaza el comprobante.<br>4. El sistema actualiza el estado del pago a "Aprobado" o "Rechazado". |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | El estado del pago cambia a "Aprobado" o "Rechazado". Si es aprobado, el equipo queda inscrito en el torneo. |

---

### 2.10 Requerimiento Funcional 10

| Campo | Descripción |
|------|-------------|
| **ID** | RF-10 |
| **Nombre del requerimiento** | Configuración del Torneo |
| **Descripción** | El sistema debe permitir al organizador definir el reglamento, fechas importantes, cierre de inscripciones, horarios de partidos, canchas y sanciones del torneo. |
| **Precondiciones** | El torneo debe existir en el sistema. |
| **Actor** | Organizador |
| **Flujo principal** | 1. El organizador accede a la configuración del torneo.<br>2. El organizador define o actualiza reglamento, fechas, horarios, canchas y sanciones.<br>3. El sistema guarda la configuración.<br>4. La información queda disponible para consulta pública. |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | La información del torneo queda disponible para consulta pública por parte de todos los usuarios. |

---

### 2.11 Requerimiento Funcional 11

| Campo | Descripción |
|------|-------------|
| **ID** | RF-11 |
| **Nombre del requerimiento** | Registro de Partido |
| **Descripción** | El sistema debe permitir al organizador registrar el marcador, goleadores y tarjetas de cada partido disputado. |
| **Precondiciones** | El partido debe estar programado en el sistema. |
| **Actor** | Organizador |
| **Flujo principal** | 1. El organizador accede al partido programado.<br>2. El organizador ingresa el marcador final, los goleadores, las tarjetas amarillas y las tarjetas rojas.<br>3. El sistema guarda la información del partido.<br>4. El sistema recalcula automáticamente la tabla de posiciones. |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | Se actualiza la información del partido y se recalcula la tabla de posiciones. |

---

### 2.12 Requerimiento Funcional 12

| Campo | Descripción |
|------|-------------|
| **ID** | RF-12 |
| **Nombre del requerimiento** | Generación de Tabla de Posiciones |
| **Descripción** | El sistema debe calcular automáticamente las estadísticas y posiciones de cada equipo con base en los resultados registrados. |
| **Precondiciones** | Deben existir partidos con resultados registrados en el sistema. |
| **Actor** | Sistema |
| **Flujo principal** | 1. El organizador registra el resultado de un partido.<br>2. El sistema procesa los datos del partido finalizado.<br>3. El sistema actualiza la tabla de posiciones con puntos, partidos jugados/ganados/empatados/perdidos, goles a favor, goles en contra y diferencia de gol.<br>4. La tabla queda disponible para consulta de todos los usuarios. |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | La tabla se actualiza con puntos, diferencia de gol y estadísticas por equipo. |

---

### 2.13 Requerimiento Funcional 13

| Campo | Descripción |
|------|-------------|
| **ID** | RF-13 |
| **Nombre del requerimiento** | Generación de Llaves Eliminatorias |
| **Descripción** | El sistema debe generar automáticamente los cruces de eliminación directa (cuartos de final, semifinal y final). |
| **Precondiciones** | El torneo debe estar en fase eliminatoria. |
| **Actor** | Sistema, Organizador |
| **Flujo principal** | 1. El organizador indica el inicio de la fase eliminatoria.<br>2. El sistema genera los cruces iniciales de manera aleatoria.<br>3. El sistema crea los partidos correspondientes a cuartos de final, semifinal y final.<br>4. Los cruces quedan disponibles para consulta de todos los usuarios. |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | Se crean automáticamente los partidos correspondientes a cuartos de final, semifinal y final. |

---

### 2.14 Requerimiento Funcional 14

| Campo | Descripción |
|------|-------------|
| **ID** | RF-14 |
| **Nombre del requerimiento** | Consulta de Información del Torneo |
| **Descripción** | El sistema debe permitir a los usuarios consultar el calendario, la tabla de posiciones, los resultados y las estadísticas del torneo. |
| **Precondiciones** | El torneo debe estar activo o en progreso. |
| **Actor** | Estudiante, Graduado, Profesor, Personal Administrativo, Familiar, Capitán, Árbitro, Organizador |
| **Flujo principal** | 1. El actor accede a la sección de información del torneo.<br>2. El actor selecciona la información que desea consultar (calendario, tabla, resultados, estadísticas).<br>3. El sistema muestra la información actualizada. |
| **Diagrama de caso de uso** | *imagen y link* |
| **Poscondiciones** | El usuario visualiza la información actualizada del torneo. |

---