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

    // ==================== CREAR TAREA ====================

    public void addTask(String taskName, String taskDescription) {
        ObjectNode taskNode = objectMapper.createObjectNode();
        taskNode.put("id", UUID.randomUUID().toString());
        taskNode.put("name", taskName);
        taskNode.put("description", taskDescription);
        tasks.add(taskNode);
    }

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

    // ==================== EDITAR TAREA ====================

    public boolean editTask(String taskId, String newName, String newDescription) {
        for (int i = 0; i < tasks.size(); i++) {
            ObjectNode task = (ObjectNode) tasks.get(i);
            if (task.get("id").asText().equals(taskId)) {
                if (newName != null && !newName.isEmpty()) {
                    task.put("name", newName);
                }
                if (newDescription != null && !newDescription.isEmpty()) {
                    task.put("description", newDescription);
                }
                return true;
            }
        }
        return false;
    }

    public void editTaskFromConsole() {
        if (tasks.size() == 0) {
            System.out.println("\nNo hay tareas para editar.");
            return;
        }

        System.out.println("\n=== Editar tarea ===");
        displayTasksWithNumbers();

        System.out.print("\nIngrese el número de la tarea a editar (o 0 para cancelar): ");
        String input = scanner.nextLine().trim();

        try {
            int taskNumber = Integer.parseInt(input);

            if (taskNumber == 0) {
                System.out.println("Operación cancelada.");
                return;
            }

            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println("⚠ Número de tarea inválido.");
                return;
            }

            ObjectNode task = (ObjectNode) tasks.get(taskNumber - 1);
            String taskId = task.get("id").asText();
            String currentName = task.get("name").asText();
            String currentDescription = task.get("description").asText();

            System.out.println("\nNombre actual: " + currentName);
            System.out.print("Nuevo nombre (Enter para mantener): ");
            String newName = scanner.nextLine().trim();

            System.out.println("Descripción actual: " + currentDescription);
            System.out.print("Nueva descripción (Enter para mantener): ");
            String newDescription = scanner.nextLine().trim();

            if (newName.isEmpty()) newName = null;
            if (newDescription.isEmpty()) newDescription = null;

            if (newName == null && newDescription == null) {
                System.out.println("No se realizaron cambios.");
                return;
            }

            if (editTask(taskId, newName, newDescription)) {
                System.out.println("✓ Tarea editada exitosamente!");
            } else {
                System.out.println("⚠ Error al editar la tarea.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠ Debe ingresar un número válido.");
        }
    }

    // ==================== ELIMINAR TAREA ====================

    public boolean deleteTask(String taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            ObjectNode task = (ObjectNode) tasks.get(i);
            if (task.get("id").asText().equals(taskId)) {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }

    public void deleteTaskFromConsole() {
        if (tasks.size() == 0) {
            System.out.println("\nNo hay tareas para eliminar.");
            return;
        }

        System.out.println("\n=== Eliminar tarea ===");
        displayTasksWithNumbers();

        System.out.print("\nIngrese el número de la tarea a eliminar (o 0 para cancelar): ");
        String input = scanner.nextLine().trim();

        try {
            int taskNumber = Integer.parseInt(input);

            if (taskNumber == 0) {
                System.out.println("Operación cancelada.");
                return;
            }

            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println("⚠ Número de tarea inválido.");
                return;
            }

            ObjectNode task = (ObjectNode) tasks.get(taskNumber - 1);
            String taskId = task.get("id").asText();
            String taskName = task.get("name").asText();

            if (deleteTask(taskId)) {
                System.out.println("✓ Tarea '" + taskName + "' eliminada exitosamente!");
            } else {
                System.out.println("⚠ Error al eliminar la tarea.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠ Debe ingresar un número válido.");
        }
    }

    // ==================== FILTRAR TAREAS ====================

    public ArrayNode filterTasks(String searchTerm) {
        ArrayNode filteredTasks = objectMapper.createArrayNode();
        String lowerSearchTerm = searchTerm.toLowerCase();

        for (int i = 0; i < tasks.size(); i++) {
            ObjectNode task = (ObjectNode) tasks.get(i);
            String name = task.get("name").asText().toLowerCase();
            String description = task.get("description").asText().toLowerCase();

            if (name.contains(lowerSearchTerm) || description.contains(lowerSearchTerm)) {
                filteredTasks.add(task);
            }
        }

        return filteredTasks;
    }

    public void filterTasksFromConsole() {
        if (tasks.size() == 0) {
            System.out.println("\nNo hay tareas para filtrar.");
            return;
        }

        System.out.println("\n=== Filtrar tareas ===");
        System.out.print("Ingrese término de búsqueda: ");
        String searchTerm = scanner.nextLine().trim();

        if (searchTerm.isEmpty()) {
            System.out.println("⚠ Debe ingresar un término de búsqueda.");
            return;
        }

        ArrayNode filteredTasks = filterTasks(searchTerm);

        if (filteredTasks.size() == 0) {
            System.out.println("\n⚠ No se encontraron tareas que coincidan con: " + searchTerm);
            return;
        }

        System.out.println("\n=== Resultados de búsqueda (" + filteredTasks.size() + " encontradas) ===");
        for (int i = 0; i < filteredTasks.size(); i++) {
            ObjectNode task = (ObjectNode) filteredTasks.get(i);
            System.out.println("\n" + (i + 1) + ". " + task.get("name").asText());
            System.out.println("   Descripción: " + task.get("description").asText());
            System.out.println("   ID: " + task.get("id").asText());
        }
    }

    // ==================== UTILIDADES ====================

    private void displayTasksWithNumbers() {
        for (int i = 0; i < tasks.size(); i++) {
            ObjectNode task = (ObjectNode) tasks.get(i);
            System.out.println((i + 1) + ". " + task.get("name").asText());
        }
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

    // ==================== GETTERS PARA TESTS ====================

    public int getTaskCount() {
        return tasks.size();
    }

    public ArrayNode getTasks() {
        return tasks;
    }

    // ==================== MENÚ PRINCIPAL ====================

    public void showMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║   GESTOR DE TAREAS             ║");
            System.out.println("╚════════════════════════════════╝");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Ver todas las tareas");
            System.out.println("3. Editar tarea");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Filtrar tareas");
            System.out.println("6. Ver JSON de tareas");
            System.out.println("7. Salir");
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
                    editTaskFromConsole();
                    break;
                case "4":
                    deleteTaskFromConsole();
                    break;
                case "5":
                    filterTasksFromConsole();
                    break;
                case "6":
                    System.out.println("\n=== JSON de Tareas ===");
                    System.out.println(getTasksAsJson());
                    break;
                case "7":
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