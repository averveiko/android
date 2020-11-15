package ru.averveyko.hw5.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.averveyko.hw5.domain.Task;

public class TaskRepository {
    private static SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static volatile TaskRepository instance;
    private final SQLiteOpenHelper dataBaseHelper;
    private List<Task> tasks;

    private TaskRepository(SQLiteOpenHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
        refresh();
    }

    private void refresh() {
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

    public void addTask(Task task) {
        SQLiteDatabase writeDb = dataBaseHelper.getWritableDatabase();

        ContentValues taskValues = new ContentValues();
        taskValues.put("TITLE", task.getTitle());
        taskValues.put("DESCRIPTION", task.getDescription());
        // need JDBC date escape format yyyy-[m]m-[d]d
        taskValues.put("DATE", DB_DATE_FORMAT.format(task.getDate()));

        writeDb.insert("TASK", null, taskValues);
        writeDb.close();
        refresh();
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
                Date date = null;
                try {
                    date = DB_DATE_FORMAT.parse(taskCursor.getString(3));
                } catch (ParseException e) {
                    Log.e("tag", e.getMessage());
                }
                Task task = new Task(
                        taskCursor.getInt(0),
                        taskCursor.getString(1),
                        taskCursor.getString(2),
                        date
                );
                tasks.add(task);
            } while (taskCursor.moveToNext());
        }
        taskCursor.close();
        readDb.close();
        return tasks;
    }
}
