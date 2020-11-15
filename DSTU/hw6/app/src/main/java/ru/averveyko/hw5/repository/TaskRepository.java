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

    public void removeTask(int id) {
        tasks.remove(id);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addDemoTasks() {
        tasks.clear();
        tasks.add(new Task("Вынести мусор", "Собрать мусор в мусорное ведро и утилизировать", new Date()));
        tasks.add(new Task("Купить продукты", "Сходить в пятерочку и купить хлеба и молока", new Date()));
        tasks.add(new Task("Сделать домашку", "Сделать самостоятельные практические работы по курсу Android разработки", new Date()));
    }
}
