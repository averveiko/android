package ru.averveyko.hw5.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Task {

    private final int id;
    private final String title;
    private final String description;
    private final Date date;

    public Task(int id, String title, String description, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getFormattedDate(SimpleDateFormat dateFormat) {
        return dateFormat.format(date);
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
}

