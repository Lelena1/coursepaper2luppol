
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    private String taskTitle;
    private String taskDescription;
    private final String taskType;
    private final LocalDateTime taskDateAndTime;
    private final RepeatabilityOfTask repeatabilityOfTask;
    public static int counter;
    private final int id;

    public Task(
            String taskTitle,
            String taskDescription,
            String taskType,
            LocalDateTime taskDateAndTime,
            RepeatabilityOfTask repeatabilityOfTask
    ) {
        this.taskTitle = ValidateUtils.validateString(taskTitle);
        this.taskDescription = ValidateUtils.validateString(taskDescription);
        this.taskType = ValidateUtils.validateString(taskType);
        this.taskDateAndTime = ValidateUtils.validateLocalDateTime(taskDateAndTime);
        this.repeatabilityOfTask = ValidateUtils.validateRepeatabilityOfTask(repeatabilityOfTask);
        counter++;
        id = counter;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskType() {
        return taskType;
    }

    public LocalDateTime getTaskDateAndTime() {
        return taskDateAndTime;
    }

    public RepeatabilityOfTask getRepeatabilityOfTask() {
        return repeatabilityOfTask;
    }

    public int getId() {
        return id;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + ". Задача \"" + taskTitle + "\", состоящая в том, чтобы "
                + taskDescription + ", тип задачи (рабочая или личная): " + taskType + ", дата и время осуществления: "
                + taskDateAndTime + ", повторяемость задачи (однократная, ежедневная, еженедельная, ежемесячная " +
                "или ежегодная: " + repeatabilityOfTask + "\n";
    }
}
