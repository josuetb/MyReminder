# MyReminder

MyReminder es una aplicación para gestionar recordatorios académicos, pensada para ayudar a los usuarios a organizar sus tareas, materias y recordatorios de manera sencilla.

## 🚀 Tecnologías utilizadas
- Kotlin (JVM)
- PostgreSQL – Base de datos relacional
- JDBC – Conexión a la base de datos
- Postman – Pruebas de endpoints
- GitHub – Control de versiones

## 🏗️ Arquitectura por capas
El proyecto sigue una arquitectura por capas bien definida:

```
src/
├── controllers   # Lógica de entrada: maneja las rutas y peticiones
├── services      # Lógica de negocio
├── repositories  # Acceso a la base de datos
├── models        # Entidades y DTOs
│   ├── entities
│   ├── requests
│   └── responses
├── mappers       # Conversión entre entidades y DTOs
├── config        # Configuración general (DB, etc)
├── routes        # Rutas centralizadas
└── exceptions    # Manejo de errores personalizados
```

## 🗄️ Estructura de la base de datos
El sistema contiene las siguientes tablas:
- **users**: Usuarios del sistema
- **subjects**: Materias
- **reminders**: Recordatorios asociados a materias y usuarios

## ✨ Funcionalidades principales
- Crear, obtener, actualizar y eliminar:
  - Usuarios
  - Materias
  - Recordatorios
- Consultar recordatorios por usuario o materia

## 📚 Endpoints disponibles
(Agrega aquí ejemplos de endpoints si lo deseas, por ejemplo usando Postman o describiendo las rutas principales)

## ⚙️ Cómo ejecutar el proyecto
1. Clona el repositorio
2. Configura tu base de datos PostgreSQL (usuario, contraseña y nombre de la base de datos en `Database.kt`)
3. Ejecuta la aplicación desde tu IDE o usando Gradle
4. Prueba los endpoints con Postman o cualquier cliente REST


