package ru.averveyko.hw5.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TasksDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tasks"; // Имя базы данных
    private static final int DB_VERSION = 1; // Версия базы данных

    public TasksDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TASK ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "TITLE TEXT, "
                + "DESCRIPTION TEXT, "
                + "DATE TEXT);");
    }

    private void insertTask(SQLiteDatabase db, String title, String description, Date date) {
        ContentValues taskValues = new ContentValues();
        taskValues.put("TITLE", title);
        taskValues.put("DESCRIPTION", description);
        // need JDBC date escape format yyyy-[m]m-[d]d
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        taskValues.put("DATE", DATE_FORMAT.format(date));

        db.insert("TASK", null, taskValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
