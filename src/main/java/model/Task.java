package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Task implements Serializable {
    private UUID id;
    private String title;
    private String description;
    private TaskPriority priority;
    private LocalDate dueDate;
    private boolean completed;

    public Task(String title, String description, TaskPriority priority, LocalDate dueDate) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = false;
    }

    // Геттеры и сеттеры

    public UUID getId() { return id; }
    public String getDescription() { return description; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }
    public void markCompleted() { this.completed = true; }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " (" + priority + ") до " + dueDate +
               (completed ? " [ЗАВЕРШЕНО]" : "");
    }
}
