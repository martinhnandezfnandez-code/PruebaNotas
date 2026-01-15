# 📋 Gestor de Tareas - Task Manager

Sistema de gestión de tareas desarrollado en Java con pruebas unitarias, creado como proyecto de aprendizaje sobre desarrollo colaborativo y testing.

## 👥 Autores

- **Manuel Barrera**
- **Martin**

> Proyecto desarrollado como ejercicio de práctica para presentación en clase.

## 🎯 Objetivo de la Actividad

Este proyecto fue desarrollado siguiendo una metodología de trabajo en equipo, donde cada integrante implementó funcionalidades específicas del sistema:

### Funcionalidades Implementadas

#### ✅ Crear Tarea (Manuel/Martin)
- Implementación del método `addTask()` y `addTaskFromConsole()`
- Validación de entrada de datos
- Generación automática de ID único (UUID)
- **Verificación**: La tarea se añade correctamente al listado con todos sus campos

#### ✏️ Editar Tarea (Manuel/Martin)
- Implementación del método `editTask()` y `editTaskFromConsole()`
- Búsqueda de tarea por ID
- Modificación de nombre y descripción
- **Verificación**: Los cambios se reflejan correctamente en el listado

#### 🗑️ Eliminar Tarea (Manuel/Martin)
- Implementación del método `deleteTask()` y `deleteTaskFromConsole()`
- Selección de tarea por número
- Confirmación de eliminación
- **Verificación**: La tarea desaparece completamente del listado

#### 🔍 Filtrar Tareas (Manuel/Martin)
- Implementación del método `filterTasks()` y `filterTasksFromConsole()`
- Búsqueda por nombre (insensible a mayúsculas)
- Búsqueda por descripción
- **Verificación**: El filtro muestra solo las tareas que coinciden con el criterio

## 🧪 Pruebas Unitarias con JUnit

El proyecto incluye un conjunto completo de pruebas unitarias que verifican cada funcionalidad:

### Suite de Pruebas Implementadas

```java
// TaskManagerTest.java
- testAddTask(): Verifica que las tareas se agregan correctamente
- testDeleteTask(): Verifica que las tareas se eliminan del listado
- testEditTask(): Verifica que los cambios se reflejan correctamente
- testFilterTasks(): Verifica que el filtro funciona como se espera
- testGetTasksAsJson(): Verifica la serialización JSON
- testEmptyTaskList(): Verifica comportamiento con lista vacía
```

### Ejecutar las Pruebas

```bash
# Compilar las pruebas
javac -cp .:junit-5.jar:jackson-databind.jar TaskManagerTest.java

# Ejecutar las pruebas
java -cp .:junit-5.jar:jackson-databind.jar org.junit.runner.JUnitCore TaskManagerTest
```

## 📝 Descripción

TaskManager es una aplicación de consola que permite gestionar tareas de manera sencilla. Cada tarea incluye un identificador único (UUID), nombre y descripción. Los datos se manejan internamente en formato JSON utilizando Jackson.

## ✨ Características Principales

- ➕ **Crear tareas**: Agregar nuevas tareas con nombre y descripción
- ✏️ **Editar tareas**: Modificar nombre y descripción de tareas existentes
- 🗑️ **Eliminar tareas**: Eliminar tareas seleccionándolas por número
- 🔍 **Filtrar tareas**: Buscar tareas por nombre o descripción
- 👁️ **Visualizar tareas**: Ver todas las tareas registradas con sus detalles
- 📄 **Exportar JSON**: Ver la representación JSON de todas las tareas
- 🎨 **Interfaz amigable**: Menú interactivo con navegación intuitiva

## 🛠️ Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal
- **Jackson Databind**: Biblioteca para manejo de JSON
- **JUnit 5**: Framework para pruebas unitarias
- **UUID**: Para generación de identificadores únicos

## 📋 Requisitos Previos

- Java JDK 8 o superior
- Maven o dependencias manuales de Jackson y JUnit

### Dependencias Maven

```xml
<dependencies>
    <!-- Jackson para JSON -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.0</version>
    </dependency>
    
    <!-- JUnit 5 para pruebas -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
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
3. Editar tarea
4. Eliminar tarea
5. Filtrar tareas
6. Ver JSON de tareas
7. Salir
```

### 1. Agregar una tarea

- Selecciona la opción `1`
- Ingresa el nombre de la tarea
- Ingresa la descripción de la tarea
- La tarea se agregará automáticamente con un ID único

### 2. Ver todas las tareas

- Selecciona la opción `2` para ver la lista completa de tareas
- Se muestran: número, nombre, descripción e ID de cada tarea

### 3. Editar una tarea

- Selecciona la opción `3`
- Elige el número de la tarea a editar
- Ingresa el nuevo nombre (o Enter para mantener el actual)
- Ingresa la nueva descripción (o Enter para mantener la actual)

### 4. Eliminar una tarea

- Selecciona la opción `4`
- Elige el número de la tarea a eliminar
- Ingresa `0` para cancelar la operación

### 5. Filtrar tareas

- Selecciona la opción `5`
- Ingresa el término de búsqueda
- Se mostrarán solo las tareas que contengan ese término en nombre o descripción

## 🏗️ Estructura del Código

### Métodos Principales

**Gestión de Tareas:**
- `addTask(String, String)`: Agrega una tarea programáticamente
- `addTaskFromConsole()`: Solicita datos por consola y agrega una tarea
- `editTask(String, String, String)`: Edita una tarea por ID
- `editTaskFromConsole()`: Interfaz interactiva para editar tareas
- `deleteTask(String)`: Elimina una tarea por su ID
- `deleteTaskFromConsole()`: Interfaz interactiva para eliminar tareas

**Consulta y Visualización:**
- `filterTasks(String)`: Filtra tareas por término de búsqueda
- `filterTasksFromConsole()`: Interfaz interactiva para filtrar tareas
- `displayTasks()`: Muestra todas las tareas con formato detallado
- `getTasksAsJson()`: Retorna las tareas en formato JSON

**Navegación:**
- `showMenu()`: Muestra el menú principal y gestiona la navegación

### Estructura de una Tarea (JSON)

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Implementar login",
  "description": "Crear sistema de autenticación de usuarios"
}
```

## 🧪 Pruebas y Verificación

### Resultados de las Pruebas

| Funcionalidad | Estado | Verificación |
|---------------|--------|--------------|
| Crear tarea | ✅ PASS | La tarea se añade correctamente al listado |
| Editar tarea | ✅ PASS | Los cambios se reflejan correctamente |
| Eliminar tarea | ✅ PASS | La tarea desaparece del listado |
| Filtrar tareas | ✅ PASS | El filtro funciona como se espera |

### Cobertura de Pruebas

- **Casos exitosos**: Todas las operaciones funcionan correctamente
- **Casos límite**: Lista vacía, IDs inválidos, búsquedas sin resultados
- **Validación de datos**: Nombres vacíos, entradas inválidas

## 📊 Metodología de Trabajo

### División de Tareas

1. **Planificación inicial**: Definición de estructura de datos y arquitectura
2. **Desarrollo en paralelo**: Cada integrante trabajó en funcionalidades específicas
3. **Integración**: Unificación del código y resolución de conflictos
4. **Testing**: Implementación de pruebas unitarias para cada funcionalidad
5. **Documentación**: Elaboración de README y comentarios en código

### Herramientas de Colaboración

- Git para control de versiones
- GitHub para repositorio compartido
- JUnit para pruebas automatizadas
- Revisión de código entre pares

## 🤝 Contribuciones

Este proyecto fue desarrollado como ejercicio académico siguiendo buenas prácticas de desarrollo colaborativo:

- Uso de control de versiones (Git)
- Implementación de pruebas unitarias
- Documentación clara del código
- División de responsabilidades
- Revisión de código en equipo

## 📄 Licencia

Este proyecto es de uso educativo y libre.

## 📞 Contacto

Proyecto desarrollado por Manuel Barrera y Martin como ejercicio de práctica académica.

---

**Fecha de creación**: Enero 2026 
**Versión**: 2.0.0  
**Última actualización**: Enero 2026
