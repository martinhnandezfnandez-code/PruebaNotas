# 📋 Gestor de Tareas - Task Manager

Sistema de gestión de tareas desarrollado en Java que permite crear, visualizar y eliminar tareas utilizando la biblioteca Jackson para manejo de JSON.

## 👥 Autores

- **Manuel Barrera**
- **Martin**

> Proyecto desarrollado como ejercicio de práctica para presentación en clase.

## 📝 Descripción

TaskManager es una aplicación de consola que permite gestionar tareas de manera sencilla. Cada tarea incluye un identificador único (UUID), nombre y descripción. Los datos se manejan internamente en formato JSON utilizando Jackson.

## ✨ Características

- ➕ **Agregar tareas**: Crear nuevas tareas con nombre y descripción
- 👁️ **Visualizar tareas**: Ver todas las tareas registradas con sus detalles
- 🗑️ **Eliminar tareas**: Eliminar tareas seleccionándolas por número
- 📄 **Exportar JSON**: Ver la representación JSON de todas las tareas
- 🎨 **Interfaz amigable**: Menú interactivo con navegación intuitiva

## 🛠️ Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal
- **Jackson Databind**: Biblioteca para manejo de JSON
- **UUID**: Para generación de identificadores únicos

## 📋 Requisitos Previos

- Java JDK 8 o superior
- Maven o dependencia manual de Jackson Databind

### Dependencia Maven
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.0</version>
</dependency>
```

## 🚀 Instalación y Uso

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/task-manager.git
cd task-manager
```

### 2. Compilar el proyecto
```bash
javac -cp .:jackson-databind.jar TaskManager.java
```

### 3. Ejecutar la aplicación
```bash
java -cp .:jackson-databind.jar TaskManager
```

## 📖 Guía de Uso

Al ejecutar la aplicación, se mostrará el siguiente menú:
```
╔════════════════════════════════╗
║   GESTOR DE TAREAS             ║
╚════════════════════════════════╝
1. Agregar tarea
2. Ver todas las tareas
3. Eliminar tarea
4. Ver JSON de tareas
5. Salir
```

### Agregar una tarea

1. Selecciona la opción `1`
2. Ingresa el nombre de la tarea
3. Ingresa la descripción de la tarea
4. La tarea se agregará automáticamente con un ID único

### Ver todas las tareas

Selecciona la opción `2` para ver la lista completa de tareas con sus detalles:
- Número de tarea
- Nombre
- Descripción
- ID único

### Eliminar una tarea

1. Selecciona la opción `3`
2. Se mostrará una lista numerada de todas las tareas
3. Ingresa el número de la tarea que deseas eliminar
4. Ingresa `0` para cancelar la operación

### Ver JSON de tareas

Selecciona la opción `4` para ver la representación JSON formateada de todas las tareas.

## 🏗️ Estructura del Código

### Métodos Principales

- `addTask(String, String)`: Agrega una tarea programáticamente (compatible con tests)
- `addTaskFromConsole()`: Solicita datos por consola y agrega una tarea
- `deleteTask(String)`: Elimina una tarea por su ID
- `deleteTaskFromConsole()`: Interfaz interactiva para eliminar tareas
- `displayTasks()`: Muestra todas las tareas con formato detallado
- `getTasksAsJson()`: Retorna las tareas en formato JSON
- `showMenu()`: Muestra el menú principal y gestiona la navegación

### Estructura de una Tarea (JSON)
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Nombre de la tarea",
  "description": "Descripción de la tarea"
}
```

## 🧪 Testing

El código mantiene compatibilidad con tests unitarios mediante el método `addTask(String, String)` que permite agregar tareas sin interacción de consola.

## 🤝 Contribuciones

Este proyecto fue desarrollado como ejercicio académico. Las sugerencias y mejoras son bienvenidas.

## 📄 Licencia

Este proyecto es de uso educativo y libre.

## 📞 Contacto

Proyecto desarrollado por Manuel Barrera y Martin como ejercicio de práctica académica.

---

**Fecha de creación**: Enero 2025  
**Versión**: 1.0.0
