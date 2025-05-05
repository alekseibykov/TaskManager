import model.Task;
import model.TaskPriority;
import service.TaskManager;
import util.FileUtils;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager manager = new TaskManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Показать все задачи");
            System.out.println("3. Завершить задачу");
            System.out.println("4. Сохранить в файл");
            System.out.println("5. Загрузить из файла");
            System.out.println("6. Выход");
            System.out.print("Выберите действие: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> addTask();
                case "2" -> showTasks();
                case "3" -> completeTask();
                case "4" -> save();
                case "5" -> load();
                case "6" -> System.exit(0);
                default -> System.out.println("Неверный ввод");
            }
        }
    }

    private static void addTask() {
        System.out.print("Введите название задачи: ");
        String title;
        title = scanner.nextLine();
        do {
            System.out.print("Название не может быть пустым. Введите название: ");
            title = scanner.nextLine();
        } while (title.isBlank());
        System.out.print("Описание: ");
        String desc = scanner.nextLine();
        System.out.print("Приоритет (LOW/MEDIUM/HIGH): ");
        TaskPriority prio = TaskPriority.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Срок (гггг-мм-дд): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        Task task = new Task(title, desc, prio, date);
        manager.addTask(task);
        System.out.println("Задача добавлена.");
    }

    private static void showTasks() {
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
    }

    private static void completeTask() {
        System.out.print("Введите ID задачи: ");
        UUID id = UUID.fromString(scanner.nextLine());
        manager.markAsCompleted(id);
    }

    private static void save() {
        try {
            FileUtils.saveTasks(manager.getAllTasks(), "tasks.dat");
            System.out.println("Сохранено.");
        } catch (Exception e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    private static void load() {
        try {
            var loaded = FileUtils.loadTasks("tasks.dat");
            for (Task task : loaded) {
                manager.addTask(task);
            }
            System.out.println("Загружено.");
        } catch (Exception e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
        }
    }
}
