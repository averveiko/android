package ru.averveyko.hw5.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ru.averveyko.hw5.domain.Task;

public class TaskRepository {
    private static volatile TaskRepository instance;
    private final SQLiteOpenHelper dataBaseHelper;
    private List<Task> tasks;

    private TaskRepository(SQLiteOpenHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
        this.tasks = getTasks();
    }

    public static TaskRepository getInstance(SQLiteOpenHelper dataBaseHelper) {
        synchronized (TaskRepository.class) {
            if (instance == null) {
                return instance = new TaskRepository(dataBaseHelper);
            }
        }
        return instance;
    }

    public void removeTask(int id) {
        SQLiteDatabase writeDb = dataBaseHelper.getWritableDatabase();
        writeDb.delete("TASK", "_id = ?", new String[] {String.valueOf(id)});
        writeDb.close();

        // Для удаления достаточно id (см. equals)
        tasks.remove(new Task(id, null, null, null));
    }

    public Task getTask(int position) {
        return tasks.get(position);
    }

    public int getTasksCount() {
        return tasks.size();
    }

    private List<Task> getTasks() {
        SQLiteDatabase readDb = dataBaseHelper.getReadableDatabase();
        Cursor taskCursor = readDb.query("TASK", new String[]{"_id", "TITLE", "DESCRIPTION", "DATE"},
                null, null, null, null, null);

        List<Task> tasks = new ArrayList<>();
        if (taskCursor.moveToFirst()) {
            do {
                Task task = new Task(
                        taskCursor.getInt(0),
                        taskCursor.getString(1),
                        taskCursor.getString(2),
                        //yyyy-[m]m-[d]d
                        Date.valueOf(taskCursor.getString(3))
                );
                tasks.add(task);
            } while (taskCursor.moveToNext());
        }
        taskCursor.close();
        readDb.close();
        return tasks;
    }
}
