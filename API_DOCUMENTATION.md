# API REST de Gestión de Tareas

API REST desarrollada con Spring Boot para la gestión de tareas.

## Entidad Tarea

```json
{
  "id": 1,
  "texto": "Texto de la tarea",
  "comentario": "Comentario adicional",
  "fechaCreacion": "2026-01-14T10:30:00",
  "finalizada": false
}
```

## Endpoints disponibles

### 1. Obtener todas las tareas
**GET** `/api/tareas`

Respuesta: `200 OK`
```json
[
  {
    "id": 1,
    "texto": "Completar proyecto",
    "comentario": "Revisar código antes de entregar",
    "fechaCreacion": "2026-01-14T10:30:00",
    "finalizada": false
  }
]
```

### 2. Obtener tarea por ID
**GET** `/api/tareas/{id}`

Respuesta: `200 OK` o `404 Not Found`
```json
{
  "id": 1,
  "texto": "Completar proyecto",
  "comentario": "Revisar código antes de entregar",
  "fechaCreacion": "2026-01-14T10:30:00",
  "finalizada": false
}
```

### 3. Crear nueva tarea
**POST** `/api/tareas`

Body:
```json
{
  "texto": "Nueva tarea",
  "comentario": "Comentario opcional"
}
```

Respuesta: `201 Created`
```json
{
  "id": 2,
  "texto": "Nueva tarea",
  "comentario": "Comentario opcional",
  "fechaCreacion": "2026-01-14T11:00:00",
  "finalizada": false
}
```

### 4. Actualizar tarea
**PUT** `/api/tareas/{id}`

Body:
```json
{
  "texto": "Tarea actualizada",
  "comentario": "Nuevo comentario",
  "finalizada": true
}
```

Respuesta: `200 OK` o `404 Not Found`

### 5. Eliminar tarea
**DELETE** `/api/tareas/{id}`

Respuesta: `204 No Content` o `404 Not Found`

### 6. Obtener tareas por estado
**GET** `/api/tareas/estado/{finalizada}`

Parámetros:
- `finalizada`: `true` o `false`

Ejemplo: `/api/tareas/estado/false`

Respuesta: `200 OK`

### 7. Buscar tareas por texto
**GET** `/api/tareas/buscar?texto={texto}`

Parámetros:
- `texto`: texto a buscar (case insensitive)

Ejemplo: `/api/tareas/buscar?texto=proyecto`

Respuesta: `200 OK`

### 8. Marcar tarea como finalizada
**PATCH** `/api/tareas/{id}/finalizar`

Respuesta: `200 OK` o `404 Not Found`

## Configuración

La aplicación se ejecuta en el puerto **8080** y se conecta a MySQL en el puerto **3307**.

Base de datos: `tareas_db`

## Ejecutar la aplicación

```bash
mvn spring-boot:run
```

## Probar los endpoints

### Con cURL:

```bash
# Crear tarea
curl -X POST http://localhost:8080/api/tareas \
  -H "Content-Type: application/json" \
  -d '{"texto":"Mi primera tarea","comentario":"Comentario de prueba"}'

# Obtener todas las tareas
curl http://localhost:8080/api/tareas

# Obtener tarea por ID
curl http://localhost:8080/api/tareas/1

# Actualizar tarea
curl -X PUT http://localhost:8080/api/tareas/1 \
  -H "Content-Type: application/json" \
  -d '{"texto":"Tarea actualizada","comentario":"Nuevo comentario","finalizada":true}'

# Marcar como finalizada
curl -X PATCH http://localhost:8080/api/tareas/1/finalizar

# Eliminar tarea
curl -X DELETE http://localhost:8080/api/tareas/1
```
