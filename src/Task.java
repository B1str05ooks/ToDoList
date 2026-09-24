import java.time.*;
import java.time.format.*;

public class Task
{
    //We need an ID, title, Description, Category, Priority, TaskStatus, duedate & createdDate

    private final int id;
    private String title;
    private String description;
    private String category;
    private Priority priority;
    private TaskStatus status;
    private LocalDate dueDate;
    private LocalDate createdDate;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    Task(int id, String title, String description, String category,
         Priority priority, TaskStatus status, LocalDate dueDate)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
        this.createdDate = LocalDate.now();
    }

    public void printSummary()
    {
        System.out.printf("  [%d]   |   %s   |   %s   |   %s   |   %s   | Due Date: %s\n   |",
                id, title, priority.getDisplayName(), status.getDisplayName(), category,
                dueDate != null ? dueDate.format(DATE_FORMAT) : "No date");
    }

    public void printDetail()
    {
        System.out.println("\n" + ("-".repeat(30)));
        System.out.println("|        TASK DETAILS         |");
        System.out.println("\n|" + ("-".repeat(30) + "|"));
        System.out.printf("|     ID:  %d    |", id);
        System.out.printf("|     Title:  %s    |", title);
        System.out.printf("|     Category:  %s    |", category);
        System.out.printf("|     Priority:  %s    |", priority.getDisplayName());
        System.out.printf("|     Status:  %s    |", status.getDisplayName());
        System.out.printf("|     Due Date:  %s    |", dueDate != null ? dueDate.format(DATE_FORMAT) : "None");
        System.out.printf("|     Created:  %s    |", createdDate.format(DATE_FORMAT));
        System.out.println("\n|" + ("-".repeat(30) + "|"));
        System.out.printf("|     Description:  %s    |", description);
        System.out.println("\n|" + ("-".repeat(30) + "|"));
    }

    // OVERRIDES

    @Override
    public String toString()
    {
        return String.format("[%d]  %s  (%s) - %s",
                id, title, priority.getDisplayName(), status.getDisplayName());
    }



    // GETTERS & SETTERS

    public int getId()              {return id;}
    public String getTitle()        {return title;}
    public String getDescription()  {return description;}
    public String getCategory()     {return category;}
    public Priority getPriority()   {return priority;}
    public TaskStatus getStatus()   {return status;}
    public LocalDate getDueDate()   {return dueDate;}
    public LocalDate getCreatedDate() {return createdDate;}


    public void setTitle(String title)              {this.title = title;}
    public void setDescription(String description)  {this.description = description;}
    public void setCategory(String category)        {this.category = category;}
    public void setPriority(Priority priority)      {this.priority = priority;}
    public void setStatus(TaskStatus status)        {this.status = status;}
    public void setDueDate(LocalDate dueDate)       {this.dueDate = dueDate;}











}
