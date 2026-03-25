# 📄 Alcance del Sistema

## 1. Sistema

* **Nombre del sistema:** TECHCUP FÚTBOL
* **Objetivo:** El sistema tiene como objetivo diseñar e implementar una plataforma web que permita gestionar de forma organizada, centralizada y transparente el torneo semestral de fútbol de los programas de Ingeniería de Sistemas, Ingeniería de Inteligencia Artificial, Ingeniería de Ciberseguridad e Ingeniería Estadística de la Escuela Colombiana de Ingeniería Julio Garavito.

## 2. Problema a resolver

Actualmente, el torneo semestral de fútbol de los programas de ingeniería de la Escuela Colombiana de Ingeniería depende completamente de procesos manuales: la inscripción se gestiona por WhatsApp, los pagos se verifican de forma manual, los resultados y la tabla de posiciones se actualizan en hojas de cálculo, y las llaves eliminatorias se organizan a mano. Esto genera desorden, retrasos, errores administrativos y confusión entre participantes y organizadores, ya que la información oficial (fechas, reglas, resultados) se encuentra dispersa y no existe un historial ni estadísticas del torneo.

## 3. Diagrama de Contexto

### 3.1 Diagrama

*imagen del diagrama de contexto*

### 3.2 Actores

| Actor / Rol              | Descripción |
|--------------------------|:-----------:|
| Estudiante               | Participante activo del torneo que puede registrarse, crear perfil deportivo, unirse a equipos y consultar información del torneo. |
| Graduado                 | Exalumno que puede registrarse como jugador y participar en equipos. |
| Profesor                 | Docente que puede registrarse y participar como jugador o capitán. |
| Personal Administrativo  | Empleado de la Escuela que puede registrarse como jugador. |
| Familiar                 | Persona externa autorizada que puede registrarse con correo Gmail. |
| Capitán                  | Usuario con permisos adicionales que puede crear y administrar un equipo, gestionar alineaciones y registrar pagos. |
| Organizador              | Encargado de administrar el torneo: crear torneos, configurar fechas, aprobar pagos y registrar resultados. |
| Árbitro                  | Usuario que consulta información de los partidos que tiene asignados. |
| Administrador            | Usuario con control total del sistema y gestión de roles. |

### 3.3 Sistemas externos

| Sistema | Descripción |
|---------|:-----------:|
| Correo institucional (ECI) | Sistema de autenticación usado por estudiantes, graduados, profesores y personal administrativo para registrarse e iniciar sesión. |
| Gmail | Servicio de correo de Google usado por familiares para registrarse e iniciar sesión en la plataforma. |
| NEQUI | Plataforma de pagos externos mediante la cual el capitán realiza el pago de inscripción del equipo fuera de la plataforma. |

## 4. Alcance del sistema

### 4.1 Dentro del sistema

Funciones que el sistema sí realiza:

1. Registro y autenticación de usuarios con control de roles y permisos (Estudiante, Graduado, Profesor, Personal Administrativo, Familiar, Capitán, Organizador, Árbitro, Administrador).
2. Creación, configuración y gestión completa del torneo (fechas, equipos, canchas, reglamento, horarios y sanciones).
3. Conformación de equipos: creación, invitación de jugadores, validación de reglas (mínimo 7, máximo 12 jugadores, composición por programa) y gestión de alineaciones.
4. Registro y seguimiento del proceso de pago de inscripción (carga de comprobante, revisión y aprobación/rechazo por el organizador).
5. Registro de resultados de partidos (marcador, goleadores, tarjetas) y cálculo automático de la tabla de posiciones y estadísticas.
6. Generación automática de llaves eliminatorias (cuartos de final, semifinal y final).
7. Consulta pública de información del torneo: calendario, tabla de posiciones, resultados, estadísticas y alineaciones.
8. Registro de auditoría de acciones relevantes dentro del sistema.

### 4.2 Fuera del sistema

Funciones que el sistema no realiza:

1. **Procesamiento de pagos:** El pago de inscripción no se realiza dentro de la plataforma; el capitán paga directamente al coordinador del evento por NEQUI o efectivo, y solo sube el comprobante al sistema.
2. **Asignación de árbitros a partidos:** El sistema no gestiona la asignación de árbitros; solo les permite consultar la información de los partidos que les corresponden.
3. **Comunicación directa entre usuarios:** La plataforma no incluye un sistema de mensajería o chat interno entre jugadores, capitanes u organizadores.
4. **Transmisión o streaming de partidos:** El sistema no ofrece funcionalidades multimedia para transmitir o grabar los encuentros del torneo.
