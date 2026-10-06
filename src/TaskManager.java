import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

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

    public List<Task> searchByKeyword(String keyword)
    {
        String lower = keyword.toLowerCase();

        return tasks.stream().filter(task -> task.getTitle().toLowerCase().contains(lower)
        || task.getDescription().toLowerCase().contains(lower) || task.getCategory().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    public List<Task> filterByPriority(Priority priority)
    {
        return tasks.stream().filter(tasks->tasks.getPriority() == priority).collect(Collectors.toList());
    }

    public List<Task> filterByStatus(TaskStatus status)
    {
        return tasks.stream().filter(tasks ->tasks.getStatus() == status).collect(Collectors.toList());
    }

    public List<Task> filterByCategory(String category)
    {
        return tasks.stream().filter(tasks -> tasks.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());

    }


    /* SORT
    _______________________________________________________________________
     */

    public List<Task> sortByPriority()
    {
        return tasks.stream().sorted(Comparator.
                comparingInt(tasks -> tasks.getPriority().getLevel())).collect(Collectors.toList());
    }

    public List<Task> sortByDueDate()
    {
        return tasks.stream().filter(tasks->tasks.getDueDate() != null)
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }

    public List<Task> sortByStatus()
    {
        return tasks.stream().
                sorted(Comparator.comparing(tasks-> tasks.getStatus().name())).
                collect(Collectors.toList());
    }

    // Stats

    public int totalTasks() { return tasks.size();}

    public int pendingTasks()
    {
        return (int) tasks.stream().filter(tasks->tasks.getStatus() == TaskStatus.PENDING).count();

    }

    public int completedTask()
    {
        return (int) tasks.stream().filter(tasks->tasks.getStatus() == TaskStatus.COMPLETED).count();
    }

    public int overdueTasks()
    {
        return (int) tasks.stream().filter(tasks->tasks.getDueDate() != null
                && tasks.getDueDate().isBefore(LocalDate.now())
                && tasks.getStatus() != TaskStatus.COMPLETED).count();
    }

    //Loading tasks from file
    public void setTasks(List<Task> loaded, int maxId)
    {
        tasks.clear();

        tasks.addAll(loaded);

        this.nextID = maxId + 1;
    }



    






}
