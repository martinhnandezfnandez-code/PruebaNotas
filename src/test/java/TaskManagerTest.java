import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

class TaskManagerTest {

    private TaskManager taskManager;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testAddTaskAddsCorrectlyToJsonList() throws Exception {
        // Arrange
        String taskName = "Completar proyecto";
        String taskDescription = "Finalizar el módulo de gestión de tareas";

        // Act
        taskManager.addTask(taskName, taskDescription);
        String jsonResult = taskManager.getTasksAsJson();

        // Assert
        assertNotNull(jsonResult, "El JSON no debería ser nulo");

        JsonNode rootNode = objectMapper.readTree(jsonResult);
        assertTrue(rootNode.isArray(), "El resultado debería ser un array JSON");
        assertEquals(1, rootNode.size(), "Debería haber exactamente una tarea");

        JsonNode taskNode = rootNode.get(0);
        assertEquals(taskName, taskNode.get("name").asText(),
                "El nombre de la tarea debería coincidir");
        assertEquals(taskDescription, taskNode.get("description").asText(),
                "La descripción de la tarea debería coincidir");
        assertTrue(taskNode.has("id"), "La tarea debería tener un ID");
    }

    @Test
    void testAddMultipleTasksToJsonList() throws Exception {
        // Arrange & Act
        taskManager.addTask("Tarea 1", "Descripción 1");
        taskManager.addTask("Tarea 2", "Descripción 2");
        taskManager.addTask("Tarea 3", "Descripción 3");

        String jsonResult = taskManager.getTasksAsJson();

        // Assert
        JsonNode rootNode = objectMapper.readTree(jsonResult);
        assertEquals(3, rootNode.size(), "Deberían haberse añadido tres tareas");

        assertEquals("Tarea 1", rootNode.get(0).get("name").asText());
        assertEquals("Tarea 2", rootNode.get(1).get("name").asText());
        assertEquals("Tarea 3", rootNode.get(2).get("name").asText());
    }
}