public class Task {
    private int id;
    private String name;
    private String description;

   /**
    * Constructor de clase Task
    * @param id numero entero
    * @param name String del nombre
    * @param description String del texto de la tarea
    * */
    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Constructor sin argumentos (necesario para Jackson)
    public Task() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}