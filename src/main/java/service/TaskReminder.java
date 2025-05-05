package service;

import model.Task;

import java.time.LocalDate;
import java.util.Collection;
import java.util.concurrent.*;

public class TaskReminder {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final Collection<Task> tasks;
    private final long checkIntervalSeconds;

    public TaskReminder(Collection<Task> tasks, long checkIntervalSeconds) {
        this.tasks = tasks;
        this.checkIntervalSeconds = checkIntervalSeconds;
    }

    public void start() {
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("🔔 Проверка задач на напоминание...");
            LocalDate now = LocalDate.now();

            tasks.stream()
                 .filter(task -> !task.isCompleted())
                 .filter(task -> task.getDueDate().isBefore(now.plusDays(1)))
                 .forEach(task -> System.out.println("⚠ Напоминание: " + task.getTitle() + " до " + task.getDueDate()));

        }, 0, checkIntervalSeconds, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }
}
