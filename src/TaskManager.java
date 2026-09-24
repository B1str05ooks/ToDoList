import java.time.LocalDate;
import java.util.*;

public class TaskManager
{
    private final List<Task> tasks = new ArrayList<>();
    private int nextID = 1;

    public Task addTask(int id, String title, String description, String category,
                        Priority priority, TaskStatus status, LocalDate dueDate)
    {
        Task task = new Task(nextID++, title, description, category, priority, status,
                dueDate);

        tasks.add(task);

        return task;
    }

    public boolean removeTask(int id)
    {
        return tasks.removeIf(task->task.getId() == id);
    }

    public Optional<Task> findById(int id)
    {
        return tasks.stream().filter(task->task.getId() == id).findFirst();
    }

    public boolean completeTask(int id)
    {
        Optional<Task> found = findById(id);

        found.ifPresent(t->t.setStatus(TaskStatus.COMPLETED));

        return found.isPresent();
    }

    public boolean startTask(int id)
    {
        Optional<Task> found = findById(id);

        found.ifPresent(task->task.setStatus(TaskStatus.IN_PROGRESS));

        return found.isPresent();
    }

    public List<Task> getAllTasks()
    {
        return Collections.unmodifiableList(tasks);
    }







}
