import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Scanner;
import java.util.UUID;

public class TaskManager {
    private ArrayNode tasks;
    private ObjectMapper objectMapper;
    private Scanner scanner;

    public TaskManager() {
        this.objectMapper = new ObjectMapper();
        this.tasks = objectMapper.createArrayNode();
        this.scanner = new Scanner(System.in);
    }

    // Método original que mantiene compatibilidad con los tests
    public void addTask(String taskName, String taskDescription) {
        ObjectNode taskNode = objectMapper.createObjectNode();
        taskNode.put("id", UUID.randomUUID().toString());
        taskNode.put("name", taskName);
        taskNode.put("description", taskDescription);
        tasks.add(taskNode);
    }

    // Nuevo método que pide datos por consola
    public void addTaskFromConsole() {
        System.out.println("\n=== Agregar nueva tarea ===");

        System.out.print("Ingrese el nombre de la tarea: ");
        String taskName = scanner.nextLine().trim();

        System.out.print("Ingrese la descripción de la tarea: ");
        String taskDescription = scanner.nextLine().trim();

        if (taskName.isEmpty()) {
            System.out.println("Error: El nombre de la tarea no puede estar vacío.");
            return;
        }

        addTask(taskName, taskDescription);
        System.out.println("✓ Tarea agregada exitosamente!");
    }

    public String getTasksAsJson() {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(tasks);
        } catch (Exception e) {
            return "[]";
        }
    }

    public void displayTasks() {
        if (tasks.size() == 0) {
            System.out.println("\nNo hay tareas registradas.");
            return;
        }

        System.out.println("\n=== Lista de Tareas ===");
        for (int i = 0; i < tasks.size(); i++) {
            ObjectNode task = (ObjectNode) tasks.get(i);
            System.out.println("\n" + (i + 1) + ". " + task.get("name").asText());
            System.out.println("   Descripción: " + task.get("description").asText());
            System.out.println("   ID: " + task.get("id").asText());
        }
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║   GESTOR DE TAREAS             ║");
            System.out.println("╚════════════════════════════════╝");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Ver todas las tareas");
            System.out.println("3. Ver JSON de tareas");
            System.out.println("4. Salir");
            System.out.print("\nSeleccione una opción: ");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    addTaskFromConsole();
                    break;
                case "2":
                    displayTasks();
                    break;
                case "3":
                    System.out.println("\n=== JSON de Tareas ===");
                    System.out.println(getTasksAsJson());
                    break;
                case "4":
                    System.out.println("\n¡Hasta luego!");
                    scanner.close();
                    return;
                default:
                    System.out.println("\n⚠ Opción inválida. Por favor, intente de nuevo.");
            }
        }
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.showMenu();
    }
}