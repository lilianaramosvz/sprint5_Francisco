# Dashboard de Productividad de Desarrolladores
Liliana Ramos Vázquez, Anna Sofía Ramírez Castro, Leonardo Mario Alberto Guillen Soria y Diana Fernanda Delgado Salcedo.

---
## Descripción del proyecto

Este proyecto consiste en una aplicación web Full Stack desarrollada para visualizar métricas de productividad de un equipo de desarrollo de software mediante un dashboard interactivo.

La aplicación permite consultar indicadores como commits realizados, tareas completadas, incidencias resueltas y líneas de código agregadas por cada desarrollador. El objetivo es ofrecer una vista centralizada del desempeño del equipo a través de métricas fáciles de interpretar mediante gráficas, tarjetas de resumen y tablas de información.

El sistema está dividido en dos componentes principales:

* **Backend:** API REST desarrollada con Spring Boot.
* **Frontend:** Aplicación web desarrollada con React y Vite.

Los datos son almacenados en una base de datos H2 en memoria y se cargan automáticamente al iniciar la aplicación.

---

## Tecnologías utilizadas

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Maven
* H2 Database
* JUnit 5
* Mockito

### Frontend

* React
* Vite
* Axios
* Recharts

---

## Funcionalidades

* Visualización de métricas globales del equipo.
* Consulta de métricas individuales por desarrollador.
* Dashboard interactivo con gráficas de rendimiento.
* Tabla detallada con información consolidada.
* API REST para consulta de datos.
* Pruebas unitarias para validar la lógica de negocio.

---

## Requisitos previos

Antes de ejecutar el proyecto es necesario contar con:

| Herramienta | Versión mínima       |
| ----------- | -------------------- |
| Java JDK    | 17                   |
| Maven       | 3.8                  |
| Node.js     | 18                   |
| npm         | Incluido con Node.js |

Verifica la instalación ejecutando:

```bash
java -version
mvn -version
node -version
npm -version
```

---

## Ejecución del proyecto

### 1. Iniciar el backend

```bash
cd backend
mvn spring-boot:run
```

Cuando aparezca el mensaje:

```text
Started ProductivityBackendApplication
```

el servidor estará disponible en:

```text
http://localhost:8080
```

---

### 2. Iniciar el frontend

En una segunda terminal:

```bash
cd frontend
npm install
npm run dev
```

La aplicación estará disponible en:

```text
http://localhost:5173
```

---

## Uso de la aplicación

Al acceder al dashboard se muestran cuatro secciones principales:

### Resumen general

Tarjetas con indicadores globales del equipo:

* Total de commits.
* Total de tareas completadas.
* Total de incidencias resueltas.
* Líneas de código agregadas.
* Número de desarrolladores registrados.

### Gráficas de rendimiento

* Commits por desarrollador.
* Comparación entre tareas completadas e incidencias resueltas.
* Comparación entre líneas agregadas y eliminadas.

### Tabla de métricas

Muestra el detalle consolidado de cada desarrollador incluyendo:

* Nombre.
* Equipo.
* Commits.
* Tareas completadas.
* Incidencias resueltas.
* Líneas agregadas.
* Líneas eliminadas.

---

## Arquitectura del sistema

El backend sigue una arquitectura por capas:

### Model

Representa las entidades de la base de datos:

* Developer
* Metric

### Repository

Gestiona el acceso a los datos mediante Spring Data JPA.

* DeveloperRepository
* MetricRepository

### Service

Implementa la lógica de negocio y la construcción de respuestas para la API.

### DTO

Objetos utilizados para transferir información al frontend.

* OverviewDTO
* DeveloperSummaryDTO

### Controller

Expone los endpoints REST consumidos por el frontend.

### Config

Configuración de CORS para permitir la comunicación entre React y Spring Boot.

---

## Base de datos

El proyecto utiliza H2 Database en memoria.

Al iniciar la aplicación se cargan automáticamente:

* 5 desarrolladores.
* 15 registros de métricas distribuidos en distintos periodos.

La consola H2 puede consultarse en:

```text
http://localhost:8080/h2-console
```

Configuración:

```text
JDBC URL: jdbc:h2:mem:productivitydb
Usuario: sa
Contraseña: (vacía)
```

---

## Endpoints disponibles

| Método | Endpoint                    | Descripción                                   |
| ------ | --------------------------- | --------------------------------------------- |
| GET    | /api/developers             | Obtiene todos los desarrolladores             |
| GET    | /api/developers/{id}        | Obtiene un desarrollador por ID               |
| GET    | /api/metrics/overview       | Obtiene métricas globales                     |
| GET    | /api/metrics/summary        | Obtiene resumen de métricas por desarrollador |
| GET    | /api/metrics/developer/{id} | Obtiene métricas individuales                 |

---

## Pruebas unitarias

Para ejecutar las pruebas:

```bash
cd backend
mvn test
```

Las pruebas fueron desarrolladas utilizando JUnit 5 y Mockito para validar la lógica de negocio de los servicios principales del sistema.

---

## Estructura del proyecto

```text
sprint5_Francisco/
├── backend/
└── frontend/
```
---
## Link del Video de Presentación

https://drive.google.com/file/d/13eisEvOi9AZ4xYpL8V--E4PQlzPTtThu/view?usp=sharing
