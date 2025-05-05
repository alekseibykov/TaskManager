package service;

import model.Task;

import java.util.*;

public class TaskManager {
    private final Map<UUID, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void removeTask(UUID id) {
        tasks.remove(id);
    }

    public Task getTask(UUID id) {
        return tasks.get(id);
    }

    public Collection<Task> getAllTasks() {
        return tasks.values();
    }

    public void markAsCompleted(UUID id) {
        Task task = tasks.get(id);
        if (task != null) {
            task.markCompleted();
        }
    }
}
