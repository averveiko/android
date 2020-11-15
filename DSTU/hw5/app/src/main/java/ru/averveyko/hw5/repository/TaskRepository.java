package ru.averveyko.hw5.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.averveyko.hw5.domain.Task;

public class TaskRepository {
    private static final TaskRepository instance = new TaskRepository();
    private final List<Task> tasks = new ArrayList<>();

    public TaskRepository() {
        addDemoTasks();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public static TaskRepository getInstance() {
        return instance;
    }

    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addDemoTasks() {
        tasks.clear();
        tasks.add(new Task("task one one one one one one one one one one one one one one one ", "task one descr", new Date()));
        tasks.add(new Task("task two", "task one two", new Date()));
        tasks.add(new Task("task three", "task one three", new Date()));
    }
}
