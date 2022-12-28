import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DailyPlanner {
    private final Map<Integer, Task> taskMap = new HashMap<>();


    public Map<Integer, Task> getTaskMap() {
        return taskMap;
    }

    public void addTaskToMap(Task task) {
        if (!taskMap.containsKey(task.getId())) {

            taskMap.put(task.getId(), task);
            System.out.println("Задача " + task.getId() + " успешно добавлена!");
        } else {
            System.out.println("Такая задача уже содержится в ежедневнике!");
        }
    }

    public void removeTaskFromMap(int id) {
        if (taskMap.containsKey(id)) {
            taskMap.remove(id);
            System.out.println("Задача успешно удалена!");
        } else {
            System.out.println("Такой задачи нет в ежедневнике");
        }
    }

    public List<Task> getTasksOnDate(LocalDate date) {
        List<Task> taskList = new ArrayList<>();
        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            Task task = entry.getValue();
            if (!task.getRepeatabilityOfTask().equals(RepeatabilityOfTask.ONE_TIME)
                    && task.getTaskDateAndTime().toLocalDate().equals(date)) {
                taskList.add(task);
            }
            if (task.getRepeatabilityOfTask().equals(RepeatabilityOfTask.DAILY)) {
                taskList.add(task);
            }
            if (task.getRepeatabilityOfTask().equals(RepeatabilityOfTask.WEEKLY) &&
                    task.getTaskDateAndTime().getDayOfWeek().equals(date.getDayOfWeek())) {
                taskList.add(task);
            }
            if (task.getRepeatabilityOfTask().equals(RepeatabilityOfTask.MONTHLY) &&
                    task.getTaskDateAndTime().getDayOfMonth() == (date.getDayOfMonth())) {
                taskList.add(task);
            }
            if (task.getRepeatabilityOfTask().equals(RepeatabilityOfTask.ANNUAL) &&
                    task.getTaskDateAndTime().getDayOfMonth() == (date.getDayOfMonth()) &&
                    task.getTaskDateAndTime().getMonth() == (date.getMonth())) {
                taskList.add(task);
            }

        }
        taskList.sort(Comparator.comparing(Task::getTaskDateAndTime));
        System.out.println(taskList);
        return taskList;
    }

    public void inputTask(Scanner scanner) {
        System.out.println("Введите название задачи:");
        scanner.nextLine();
        String taskTitle = scanner.nextLine();
        System.out.println(taskTitle);

        System.out.println("Введите описание задачи:");
        String taskDescription = scanner.nextLine();
        System.out.println(taskDescription);

        System.out.println("Введите тип задачи (рабочая или личная):");
        String taskType = scanner.nextLine();
        System.out.println(taskType);

        System.out.println("Введите дату и время проведения задачи (в формате дд.мм.гггг чч:мм):");
        LocalDateTime taskDateAndTime = null;
        boolean isCorrectTheDateFormat = false;
        while (!isCorrectTheDateFormat) {
            try {
                taskDateAndTime = LocalDateTime.parse(scanner.nextLine(), Task.DATE_TIME_FORMATTER);
                System.out.println(taskDateAndTime);
                isCorrectTheDateFormat = true;
            } catch (DateTimeParseException e) {
                System.out.println("Введите дату еще раз");
            }
        }
        System.out.println("Введите повторяемость задачи (однократная, ежедневная, " +
                "еженедельная, ежемесячная, ежегодная (введите соответствующее число):");
        System.out.println("\t 0 -> " + RepeatabilityOfTask.ONE_TIME);
        System.out.println("\t 1 -> " + RepeatabilityOfTask.DAILY);
        System.out.println("\t 2 -> " + RepeatabilityOfTask.WEEKLY);
        System.out.println("\t 3 -> " + RepeatabilityOfTask.MONTHLY);
        System.out.println("\t 4 -> " + RepeatabilityOfTask.ANNUAL);

        Task task;
        RepeatabilityOfTask repeatabilityOfTask;
        int num = Integer.parseInt(scanner.nextLine());
        repeatabilityOfTask = RepeatabilityOfTask.values()[num];
        System.out.println(repeatabilityOfTask);

        task = new Task(taskTitle, taskDescription, taskType, taskDateAndTime, repeatabilityOfTask);
        this.addTaskToMap(task);
        System.out.println("Обновленный список задач:");
        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public void removeTask(Scanner scanner) {
        DailyPlanner dailyPlanner = new DailyPlanner();
        System.out.println("Введите id задачи:");

        int id = scanner.nextInt();
        if (!taskMap.containsKey(id)) {
            System.out.println("Такой задачи нет!");
        }
        dailyPlanner.removeTaskFromMap(id);
        System.out.println("Задача успешно удалена!");
    }

    public void getTasksOnDate(Scanner scanner) {
        DailyPlanner dailyPlanner = new DailyPlanner();
        scanner.nextLine();
        System.out.print("Введите дату выполнения задачи(в формате дд.мм.гггг):");
        LocalDate date = null;
        boolean isCorrectTheDate = false;
        while (!isCorrectTheDate) {
            try {
                date = LocalDate.parse(scanner.nextLine(), Task.DATE_FORMATTER);
                isCorrectTheDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Введите дату еще раз");
            }
        }
        System.out.println(dailyPlanner.getTasksOnDate(date));
    }
}