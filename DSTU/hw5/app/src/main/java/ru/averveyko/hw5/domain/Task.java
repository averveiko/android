package ru.averveyko.hw5.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Task {
    private final String title;
    private final String description;
    private final Date date;

    public Task(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
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

    public String getFormattedDate() {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        return DATE_FORMAT.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return title.equals(task.title) &&
                description.equals(task.description) &&
                date.equals(task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, date);
    }
}

