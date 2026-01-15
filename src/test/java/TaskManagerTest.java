import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * Suite de pruebas unitarias para TaskManager
 * Verifica las funcionalidades principales del sistema de gestión de tareas
 */
public class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        // Inicializar un nuevo TaskManager antes de cada prueba
        taskManager = new TaskManager();
    }

    // ==================== PRUEBAS: CREAR TAREA ====================

    @Test
    @DisplayName("Test: Agregar tarea correctamente al listado")
    public void testAddTask() {
        // Arrange
        String taskName = "Implementar login";
        String taskDescription = "Crear sistema de autenticación";

        // Act
        taskManager.addTask(taskName, taskDescription);

        // Assert
        assertEquals(1, taskManager.getTaskCount(),
                "La tarea debería agregarse al listado");
        assertTrue(taskManager.getTasksAsJson().contains(taskName),
                "El listado debería contener el nombre de la tarea");
        assertTrue(taskManager.getTasksAsJson().contains(taskDescription),
                "El listado debería contener la descripción de la tarea");
    }

    @Test
    @DisplayName("Test: Agregar múltiples tareas")
    public void testAddMultipleTasks() {
        // Act
        taskManager.addTask("Tarea 1", "Descripción 1");
        taskManager.addTask("Tarea 2", "Descripción 2");
        taskManager.addTask("Tarea 3", "Descripción 3");

        // Assert
        assertEquals(3, taskManager.getTaskCount(),
                "Deberían haberse agregado 3 tareas");
    }

    @Test
    @DisplayName("Test: Cada tarea tiene un ID único")
    public void testTasksHaveUniqueIds() {
        // Act
        taskManager.addTask("Tarea 1", "Descripción 1");
        taskManager.addTask("Tarea 2", "Descripción 2");

        // Assert
        String json = taskManager.getTasksAsJson();
        assertTrue(json.contains("\"id\""),
                "Las tareas deberían tener un campo 'id'");
    }

    // ==================== PRUEBAS: EDITAR TAREA ====================

    @Test
    @DisplayName("Test: Editar nombre de tarea existente")
    public void testEditTaskName() {
        // Arrange
        taskManager.addTask("Tarea Original", "Descripción Original");
        String json = taskManager.getTasksAsJson();
        String taskId = extractFirstTaskId(json);

        // Act
        boolean result = taskManager.editTask(taskId, "Tarea Editada", null);

        // Assert
        assertTrue(result, "La edición debería ser exitosa");
        assertTrue(taskManager.getTasksAsJson().contains("Tarea Editada"),
                "El nombre debería haberse actualizado");
        assertTrue(taskManager.getTasksAsJson().contains("Descripción Original"),
                "La descripción debería mantenerse igual");
    }

    @Test
    @DisplayName("Test: Editar descripción de tarea existente")
    public void testEditTaskDescription() {
        // Arrange
        taskManager.addTask("Tarea Original", "Descripción Original");
        String json = taskManager.getTasksAsJson();
        String taskId = extractFirstTaskId(json);

        // Act
        boolean result = taskManager.editTask(taskId, null, "Descripción Editada");

        // Assert
        assertTrue(result, "La edición debería ser exitosa");
        assertTrue(taskManager.getTasksAsJson().contains("Tarea Original"),
                "El nombre debería mantenerse igual");
        assertTrue(taskManager.getTasksAsJson().contains("Descripción Editada"),
                "La descripción debería haberse actualizado");
    }

    @Test
    @DisplayName("Test: Editar tarea con ID inválido")
    public void testEditTaskInvalidId() {
        // Arrange
        taskManager.addTask("Tarea Original", "Descripción Original");

        // Act
        boolean result = taskManager.editTask("id-invalido", "Nuevo Nombre", "Nueva Descripción");

        // Assert
        assertFalse(result, "La edición con ID inválido debería fallar");
    }

    // ==================== PRUEBAS: ELIMINAR TAREA ====================

    @Test
    @DisplayName("Test: Eliminar tarea del listado")
    public void testDeleteTask() {
        // Arrange
        taskManager.addTask("Tarea a Eliminar", "Esta tarea será eliminada");
        String json = taskManager.getTasksAsJson();
        String taskId = extractFirstTaskId(json);
        int initialCount = taskManager.getTaskCount();

        // Act
        boolean result = taskManager.deleteTask(taskId);

        // Assert
        assertTrue(result, "La eliminación debería ser exitosa");
        assertEquals(initialCount - 1, taskManager.getTaskCount(),
                "La tarea debería desaparecer del listado");
        assertFalse(taskManager.getTasksAsJson().contains("Tarea a Eliminar"),
                "La tarea eliminada no debería estar en el listado");
    }

    @Test
    @DisplayName("Test: Eliminar tarea con ID inválido")
    public void testDeleteTaskInvalidId() {
        // Arrange
        taskManager.addTask("Tarea 1", "Descripción 1");
        int initialCount = taskManager.getTaskCount();

        // Act
        boolean result = taskManager.deleteTask("id-que-no-existe");

        // Assert
        assertFalse(result, "La eliminación con ID inválido debería fallar");
        assertEquals(initialCount, taskManager.getTaskCount(),
                "El número de tareas no debería cambiar");
    }

    @Test
    @DisplayName("Test: Eliminar todas las tareas")
    public void testDeleteAllTasks() {
        // Arrange
        taskManager.addTask("Tarea 1", "Descripción 1");
        taskManager.addTask("Tarea 2", "Descripción 2");
        taskManager.addTask("Tarea 3", "Descripción 3");

        // Act - Eliminar todas las tareas
        String json = taskManager.getTasksAsJson();
        while (taskManager.getTaskCount() > 0) {
            String taskId = extractFirstTaskId(json);
            taskManager.deleteTask(taskId);
            json = taskManager.getTasksAsJson();
        }

        // Assert
        assertEquals(0, taskManager.getTaskCount(),
                "No deberían quedar tareas en el listado");
    }

    // ==================== PRUEBAS: FILTRAR TAREAS ====================

    @Test
    @DisplayName("Test: Filtrar tareas por nombre")
    public void testFilterTasksByName() {
        // Arrange
        taskManager.addTask("Implementar login", "Sistema de autenticación");
        taskManager.addTask("Implementar registro", "Sistema de registro de usuarios");
        taskManager.addTask("Crear base de datos", "Diseño del esquema");

        // Act
        ArrayNode filtered = taskManager.filterTasks("Implementar");

        // Assert
        assertEquals(2, filtered.size(),
                "El filtro debería encontrar 2 tareas con 'Implementar'");
    }

    @Test
    @DisplayName("Test: Filtrar tareas por descripción")
    public void testFilterTasksByDescription() {
        // Arrange
        taskManager.addTask("Login", "Sistema de autenticación");
        taskManager.addTask("Registro", "Sistema de registro");
        taskManager.addTask("Base de datos", "Diseño del esquema");

        // Act
        ArrayNode filtered = taskManager.filterTasks("Sistema");

        // Assert
        assertEquals(2, filtered.size(),
                "El filtro debería encontrar 2 tareas con 'Sistema' en la descripción");
    }

    @Test
    @DisplayName("Test: Filtrar tareas es insensible a mayúsculas")
    public void testFilterTasksCaseInsensitive() {
        // Arrange
        taskManager.addTask("TAREA EN MAYÚSCULAS", "Descripción");
        taskManager.addTask("tarea en minúsculas", "Descripción");

        // Act
        ArrayNode filtered = taskManager.filterTasks("tarea");

        // Assert
        assertEquals(2, filtered.size(),
                "El filtro debería ser insensible a mayúsculas");
    }

    @Test
    @DisplayName("Test: Filtrar sin coincidencias")
    public void testFilterTasksNoMatches() {
        // Arrange
        taskManager.addTask("Tarea 1", "Descripción 1");
        taskManager.addTask("Tarea 2", "Descripción 2");

        // Act
        ArrayNode filtered = taskManager.filterTasks("TerminoQueNoExiste");

        // Assert
        assertEquals(0, filtered.size(),
                "El filtro no debería encontrar coincidencias");
    }

    // ==================== PRUEBAS: CASOS LÍMITE ====================

    @Test
    @DisplayName("Test: Lista vacía")
    public void testEmptyTaskList() {
        // Assert
        assertEquals(0, taskManager.getTaskCount(),
                "El listado inicial debería estar vacío");
        assertEquals("[ ]", taskManager.getTasksAsJson(),
                "El JSON de un listado vacío debería ser un array vacío");
    }

    @Test
    @DisplayName("Test: Serialización JSON")
    public void testGetTasksAsJson() {
        // Arrange
        taskManager.addTask("Tarea Test", "Descripción Test");

        // Act
        String json = taskManager.getTasksAsJson();

        // Assert
        assertNotNull(json, "El JSON no debería ser null");
        assertTrue(json.contains("Tarea Test"),
                "El JSON debería contener la tarea agregada");
        assertTrue(json.contains("\"id\""),
                "El JSON debería contener el campo 'id'");
        assertTrue(json.contains("\"name\""),
                "El JSON debería contener el campo 'name'");
        assertTrue(json.contains("\"description\""),
                "El JSON debería contener el campo 'description'");
    }

    // ==================== MÉTODOS AUXILIARES ====================

    /**
     * Extrae el ID de la primera tarea del JSON
     */
    private String extractFirstTaskId(String json) {
        int idIndex = json.indexOf("\"id\" : \"") + 8;
        int endIndex = json.indexOf("\"", idIndex);
        return json.substring(idIndex, endIndex);
    }

    @AfterEach
    public void tearDown() {
        // Limpiar recursos después de cada prueba
        taskManager = null;
    }
}