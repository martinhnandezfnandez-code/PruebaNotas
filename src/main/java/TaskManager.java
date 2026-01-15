import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();
    private int nextId = 1;

    public void addTask(String name, String description) {
        tasks.add(new Task(nextId++, name, description));
    }

    public String getTasksAsJson() throws Exception {
        return mapper.writeValueAsString(tasks);
    }
}