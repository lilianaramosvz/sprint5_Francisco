# Dashboard de Productividad de Desarrolladores

Sistema Full Stack para visualizar metricas de productividad de equipos de software.

## Tecnologias

| Capa | Tecnologia |
|------|------------|
| Backend | Spring Boot 3.3, Spring Data JPA |
| Base de datos | H2 (en memoria) |
| Frontend | React 18, Vite |
| Graficas | Recharts |
| HTTP | Axios |
| Pruebas | JUnit 5, Mockito |

---

## Arquitectura del Sistema

```
┌──────────────────────┐    HTTP/REST    ┌──────────────────────────────────┐
│  React (Vite)        │ ─────────────► │  Spring Boot                     │
│  port: 5173          │                │  Controller -> Service -> Repo    │
│                      │ ◄───────────── │  H2 en memoria                   │
└──────────────────────┘    JSON        └──────────────────────────────────┘
```

### Estructura del Backend (arquitectura por capas)

```
backend/src/main/java/com/dashboard/productivity/
├── config/
│   └── CorsConfig.java          ← Configuracion CORS para el frontend
├── controller/
│   ├── DeveloperController.java ← Endpoints REST de desarrolladores
│   └── MetricController.java   ← Endpoints REST de metricas
├── service/
│   ├── DeveloperService.java    ← Logica de negocio de desarrolladores
│   └── MetricService.java       ← Logica de negocio de metricas
├── repository/
│   ├── DeveloperRepository.java ← Acceso a datos de Developer
│   └── MetricRepository.java    ← Acceso a datos + queries JPQL
├── model/
│   ├── Developer.java           ← Entidad JPA
│   └── Metric.java              ← Entidad JPA (relacion ManyToOne)
└── dto/
    ├── DeveloperSummaryDTO.java ← Resumen agregado por desarrollador
    └── OverviewDTO.java         ← Totales globales del dashboard
```

### Estructura del Frontend (modular)

```
frontend/src/
├── api/
│   └── api.js                  ← Llamadas HTTP centralizadas con Axios
├── components/
│   ├── Navbar.jsx              ← Barra de navegacion
│   ├── MetricCard.jsx          ← Tarjeta de metrica reutilizable
│   ├── CommitsChart.jsx        ← Grafica de commits (BarChart)
│   ├── TasksChart.jsx          ← Grafica de tareas e incidencias (BarChart)
│   ├── LinesChart.jsx          ← Grafica de lineas de codigo (BarChart)
│   └── DeveloperTable.jsx      ← Tabla de resumen por desarrollador
└── pages/
    └── Dashboard.jsx           ← Pagina principal (consume API, orquesta componentes)
```

---

## Endpoints REST

| Metodo | URL | Descripcion |
|--------|-----|-------------|
| GET | `/api/developers` | Lista todos los desarrolladores |
| GET | `/api/developers/{id}` | Obtiene un desarrollador por ID |
| GET | `/api/metrics/overview` | Totales globales del dashboard |
| GET | `/api/metrics/summary` | Resumen agregado por desarrollador |
| GET | `/api/metrics/developer/{id}` | Metricas individuales de un desarrollador |

---

## Como ejecutar

### Requisitos previos
- Java 17+
- Maven 3.8+
- Node.js 18+

### 1. Backend

```bash
cd backend
mvn spring-boot:run
```

- API disponible en: http://localhost:8080
- Consola H2: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:productivitydb`
  - User: `sa` / Password: (vacio)

### 2. Frontend

```bash
cd frontend
npm install
npm run dev
```

- Dashboard en: http://localhost:5173

### 3. Pruebas unitarias

```bash
cd backend
mvn test
```

---

## Metricas del Dashboard

- **Commits por desarrollador** — grafica de barras verticales
- **Tareas completadas e incidencias resueltas** — barras agrupadas por desarrollador
- **Lineas de codigo anadidas vs eliminadas** — barras comparativas
- **Tarjetas de resumen** — totales globales (commits, tareas, incidencias, lineas, devs)
- **Tabla detallada** — todas las metricas por desarrollador con insignia de equipo

---

## Analisis: Errores Comunes y Como se Evitaron

### 1. Logica de negocio en el controlador
**Problema**: Controladores con SQL directo o calculos complejos.  
**Solucion aplicada**: Cada controlador solo delega al servicio correspondiente. Los calculos (SUM, COUNT) viven en `MetricService`.

### 2. Consultas N+1
**Problema**: Cargar todos los desarrolladores y luego hacer una query por cada uno para sus metricas.  
**Solucion aplicada**: Una sola query JPQL con `JOIN` y `GROUP BY` en `MetricRepository.findDeveloperSummaries()` devuelve todo en una sola consulta.

### 3. Exposicion directa de entidades JPA en la API
**Problema**: Serializar `Developer` y `Metric` directamente expone la estructura interna de la base de datos y puede causar referencias circulares.  
**Solucion aplicada**: `DeveloperSummaryDTO` y `OverviewDTO` encapsulan exactamente lo que el frontend necesita.

### 4. CORS no configurado
**Problema**: El navegador bloquea las peticiones del frontend (5173) al backend (8080) por Same-Origin Policy.  
**Solucion aplicada**: `CorsConfig` permite explicitamente el origen del frontend.

### 5. Frontend monolitico
**Problema**: Todo en un unico componente hace el codigo imposible de mantener.  
**Solucion aplicada**: Cada responsabilidad es un componente independiente (`MetricCard`, `CommitsChart`, etc.) y las llamadas HTTP estan centralizadas en `api/api.js`.

---

## Propuestas de Mejora

### Propuesta 1: Paginacion y filtros en la API

**Problema identificado**: La API retorna todos los registros sin paginacion. Con cientos de desarrolladores o miles de registros de metricas, la respuesta sera lenta y consumira mucha memoria.

**Justificacion tecnica**: El principio de eficiencia dicta que no se deben transferir mas datos de los necesarios. La paginacion es un patron estandar en APIs REST.

**Beneficios esperados**:
- Reduccion del tiempo de respuesta inicial
- Menor consumo de memoria en el servidor
- Posibilidad de busqueda y filtrado por equipo o rango de fechas

**Posible implementacion**:
```java
// En el repositorio:
Page<Metric> findByDeveloperId(Long id, Pageable pageable);

// En el controlador:
@GetMapping("/developer/{id}")
public ResponseEntity<Page<Metric>> getByDeveloper(
    @PathVariable Long id,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) { ... }
```

---

### Propuesta 2: Autenticacion y autorizacion con JWT

**Problema identificado**: Todos los endpoints son publicos. Cualquier persona puede ver los datos de productividad del equipo, que son informacion sensible de recursos humanos.

**Justificacion tecnica**: La seguridad es un requisito no funcional critico. Los datos de desempeno individual deben estar protegidos por roles (admin ve todo, developer solo ve sus propias metricas).

**Beneficios esperados**:
- Control de acceso por rol (ADMIN, MANAGER, DEVELOPER)
- Trazabilidad de quien accede a los datos
- Cumplimiento de buenas practicas de seguridad

**Posible implementacion**:
- Backend: Spring Security + JWT (biblioteca `jjwt`)
- Frontend: Axios interceptors que agregan el token en cada peticion
- Endpoint `/api/auth/login` que retorna el JWT

---

### Propuesta 3: Graficas de tendencia temporal (evolucion mensual)

**Problema identificado**: El dashboard solo muestra totales acumulados. No es posible saber si un desarrollador mejoro o empeoro con el tiempo, ni identificar periodos de baja productividad.

**Justificacion tecnica**: Las metricas estaticas no permiten detectar tendencias. Una vista temporal es fundamental para la toma de decisiones de gestion.

**Beneficios esperados**:
- Identificacion de meses de baja productividad
- Visualizacion del crecimiento o decrecimiento del equipo
- Base para alertas automaticas si una metrica cae por debajo de un umbral

**Posible implementacion**:
```java
// Query agrupada por mes:
@Query("SELECT new com.dashboard.productivity.dto.MonthlyTrendDTO(" +
       "YEAR(m.date), MONTH(m.date), SUM(m.commits)) " +
       "FROM Metric m WHERE m.developer.id = :id " +
       "GROUP BY YEAR(m.date), MONTH(m.date) ORDER BY YEAR(m.date), MONTH(m.date)")
List<MonthlyTrendDTO> findMonthlyTrend(@Param("id") Long developerId);
```
- Frontend: `LineChart` de Recharts con el eje X como "Mes/Año"

---

## Pruebas Unitarias Implementadas

Se cubren las dos capas de servicio con JUnit 5 + Mockito:

### DeveloperServiceTest
| Prueba | Descripcion |
|--------|-------------|
| `findAll_returnsAllDevelopers` | Verifica que el servicio retorna la lista del repositorio |
| `findById_existingId_returnsDeveloper` | Verifica retorno correcto cuando el ID existe |
| `findById_nonExistingId_throwsException` | Verifica que lanza excepcion cuando el ID no existe |

### MetricServiceTest
| Prueba | Descripcion |
|--------|-------------|
| `getOverview_returnsCorrectTotals` | Verifica que el DTO agrega correctamente los totales |
| `getDeveloperSummaries_returnsListFromRepository` | Verifica la delegacion al repositorio |
| `getDeveloperSummaries_emptyRepository_returnsEmptyList` | Caso borde: sin datos |
