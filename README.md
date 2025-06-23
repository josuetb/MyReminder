# ⏰ MyReminder

**MyReminder** es una aplicación para gestionar recordatorios académicos, permitiendo a los usuarios registrar, consultar y organizar recordatorios, materias y usuarios.

---

## 🚀 Tecnologías utilizadas

- Kotlin + Spring Boot
- PostgreSQL (usando Docker)
- JPA / Hibernate
- Postman para pruebas de API
- Gradle (gestión de dependencias)

---

## 📁 Estructura del proyecto

```
├── controllers         # Lógica de control de endpoints
├── services            # Lógica de negocio
├── repositories        # Acceso a datos (JPA)
├── models
│   ├── entities        # Entidades JPA
│   ├── requests        # DTOs de entrada
│   └── responses       # DTOs de salida
├── mappers             # Conversión entre entidad y DTO
├── routes              # Rutas centralizadas
├── exceptions          # Manejo de errores personalizados
└── application.properties  # Configuración del proyecto
```

---

## 🧪 Endpoints principales

| Recurso        | Método  | Ruta                                         | Descripción                           |
|--------------- |---------|----------------------------------------------|---------------------------------------|
| **Usuarios**   | GET     | `/api/myreminder/users`                      | Obtener todos los usuarios            |
|                | GET     | `/api/myreminder/users/{id}`                 | Obtener un usuario por ID             |
|                | POST    | `/api/myreminder/users`                      | Crear un nuevo usuario                |
|                | PUT     | `/api/myreminder/users/{id}`                 | Actualizar usuario por ID             |
|                | DELETE  | `/api/myreminder/users/{id}`                 | Eliminar usuario por ID               |
| **Recordatorios**| GET   | `/api/myreminder/reminders`                  | Obtener todos los recordatorios       |
|                | GET     | `/api/myreminder/reminders/{id}`             | Obtener un recordatorio por ID        |
|                | POST    | `/api/myreminder/reminders`                  | Crear un nuevo recordatorio           |
|                | PUT     | `/api/myreminder/reminders/{id}`             | Actualizar recordatorio por ID        |
|                | DELETE  | `/api/myreminder/reminders/{id}`             | Eliminar recordatorio por ID          |
| **Materias**   | GET     | `/api/myreminder/subjects`                   | Obtener todas las materias            |
|                | GET     | `/api/myreminder/subjects/{id}`              | Obtener una materia por ID            |
|                | POST    | `/api/myreminder/subjects`                   | Crear una nueva materia               |
|                | PUT     | `/api/myreminder/subjects/{id}`              | Actualizar materia por ID             |
|                | DELETE  | `/api/myreminder/subjects/{id}`              | Eliminar materia por ID               |

---

## Paso 1: Clonar el repositorio

Clona este repositorio en tu máquina local con el siguiente comando:

```bash
git clone https://github.com/josuetb/MyReminder.git
```

Luego navega a la carpeta del proyecto:

```bash
cd MyReminder
```

---

## Paso 2: Configuración de la base de datos

El proyecto usa PostgreSQL dentro de un contenedor Docker. Puedes levantar la base de datos con:

```bash
docker-compose up -d
```

o para visualizar los logs

```bash
docker-compose up
```

---

## Paso 3: Cómo ejecutar el proyecto

Puedes ejecutar el proyecto de dos formas:

### Opción 1: Desde la terminal

```bash
./gradlew bootRun
```

### Opción 2: Desde IntelliJ IDEA

1. Abre el proyecto en IntelliJ IDEA.
2. Ve a la ruta `src/main/kotlin/com/myreminder/MyReminderApplication.kt`.
3. Haz clic derecho sobre el archivo y selecciona **Run 'MyReminderApplicationKt'** o haz clic en el botón verde de "Run" que aparece al lado del nombre de la clase.

---

## Paso 4: Pruebas con Postman

Puedes crear tus propias peticiones en Postman usando los endpoints de la tabla anterior. Aquí tienes ejemplos de cuerpos para las peticiones POST:

### Crear usuario
```json
{
  "id": "u1",
  "name": "Josue",
  "email": "josue@email.com"
}
```

### Crear materia
```json
{
  "id": 1,
  "name": "Matemáticas",
  "userId": "u1"
}
```

### Crear recordatorio
```json
{
  "id": 1,
  "userId": "u1",
  "subjectId": 1,
  "title": "Examen Final",
  "description": "Estudiar para el examen final de matemáticas",
  "date": "2024-06-25",
  "time": "10:00",
  "priority": "Alta",
  "priorityColor": "Rojo",
  "type": "Examen",
  "status": "Pendiente",
  "notificationId": 100
}
```

---

## Colección de Postman

Dentro del proyecto se incluye el archivo `Postman/MyReminder.postman_collection.json`, el cual contiene las peticiones necesarias para probar los principales endpoints del sistema.

Puedes importarlo en Postman para facilitar el proceso de pruebas.


