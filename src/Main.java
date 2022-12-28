

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Task task1 = new Task(
                "Курсы",
                "Изучить рынок образовательных программ, изучить методические подходы " +
                        "образовательных программ, выявить тенденции рынка труда, выбрать обучающий курс",
                "рабочая",
                LocalDateTime.now().minusMonths(4),
                RepeatabilityOfTask.ANNUAL
        );
        Task task2 = new Task(
                "Оплата",
                "Перечислить деньги за обучение по программе Java-разработчик",
                "рабочая",
                LocalDateTime.now().minusMonths(3),
                RepeatabilityOfTask.ONE_TIME
        );
        Task task3 = new Task(
                "Урок",
                "Посмотреть видео к уроку, изучить теоретическую часть, изучить особенности кода по теме",
                "рабочая",
                LocalDateTime.now().minusDays(85),
                RepeatabilityOfTask.WEEKLY
        );
        Task task4 = new Task(
                "Домашка",
                "Ознакомиться с материалами урока, изучить шпаргалку, решить задачи, проверить решение по критериям," +
                        "загрузить ссылку на удаленный репозиторий на платформу Скайпро",
                "рабочая",
                LocalDateTime.now().minusDays(82),
                RepeatabilityOfTask.WEEKLY
        );
        Task task5 = new Task(
                "Q&A",
                "Предварительно ознакомиться с материалами урока, изучить шпаргалку, " +
                        "решить задачи, подготовить вопросы к встрече с наставником, получить ссылку на встречу в ZOOM",
                "рабочая",
                LocalDateTime.now().minusDays(80),
                RepeatabilityOfTask.WEEKLY
        );
        Task task6 = new Task(
                "Магазин",
                "Составить список продуктов и нужных вещей, купить необходимое в магазинах",
                "личная",
                LocalDateTime.now().minusMonths(3),
                RepeatabilityOfTask.DAILY
        );

        Task task7 = new Task(
                "Новый год",
                "Украсить дом, купить подарки, приготовить праздничный стол, придумать развлечения",
                "личная",
                LocalDateTime.now().plusDays(7),
                RepeatabilityOfTask.ANNUAL
        );


        Map<Integer, Task> taskMap = new HashMap<>();
        taskMap.put(task1.getId(), task1);
        taskMap.put(task2.getId(), task2);
        taskMap.put(task3.getId(), task3);
        taskMap.put(task4.getId(), task4);
        taskMap.put(task5.getId(), task5);
        taskMap.put(task6.getId(), task6);
        taskMap.put(task7.getId(), task7);

        System.out.println();
        System.out.println("Список задач:");
        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println();

        List<LocalDateTime> dateTimes = new ArrayList<>();
        dateTimes.add(0, task1.getTaskDateAndTime());
        dateTimes.add(1, task2.getTaskDateAndTime());
        dateTimes.add(2, task3.getTaskDateAndTime());
        dateTimes.add(3, task4.getTaskDateAndTime());
        dateTimes.add(4, task5.getTaskDateAndTime());
        dateTimes.add(5, task6.getTaskDateAndTime());
        dateTimes.add(6, task7.getTaskDateAndTime());
        System.out.println(dateTimes);

        System.out.println(taskMap.get(1));
        System.out.println("Удаляем первую задачу");
        taskMap.remove(1);
        System.out.println(taskMap.toString());
        System.out.println("Добавляем первую задачу");
        taskMap.put(1, task1);
        System.out.println(taskMap);
        DailyPlanner dailyPlanner = new DailyPlanner();
        dailyPlanner.addTaskToMap(task1);
        dailyPlanner.addTaskToMap(task1);
        dailyPlanner.removeTaskFromMap(1);
        dailyPlanner.removeTaskFromMap(1);
        dailyPlanner.addTaskToMap(task1);
        dailyPlanner.addTaskToMap(task2);
        dailyPlanner.addTaskToMap(task3);
        dailyPlanner.addTaskToMap(task4);
        dailyPlanner.addTaskToMap(task5);
        dailyPlanner.addTaskToMap(task6);
        dailyPlanner.addTaskToMap(task7);
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
        taskList.add(task6);
        taskList.add(task7);
        taskList.sort(Comparator.comparing(Task::getTaskDateAndTime));
        System.out.println(taskList);
        System.out.println("Получить задачу на текущую (для примера) дату: " + LocalDate.now());
        dailyPlanner.getTasksOnDate(LocalDate.now());


        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            dailyPlanner.inputTask(scanner);
                            break;
                        case 2:
                            dailyPlanner.removeTask(scanner);
                            break;
                        case 3:
                            dailyPlanner.getTasksOnDate(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    public static void printMenu() {
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Получить задачу на указанный день");
        System.out.println("0. Выход");

    }

}