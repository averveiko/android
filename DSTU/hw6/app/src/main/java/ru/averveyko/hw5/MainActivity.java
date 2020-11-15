package ru.averveyko.hw5;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;

import ru.averveyko.hw5.domain.Task;
import ru.averveyko.hw5.repository.TaskRepository;
import ru.averveyko.hw5.sqlite.TasksDataBaseHelper;

public class MainActivity extends AppCompatActivity {

    private final SQLiteOpenHelper dataBaseHelper = new TasksDataBaseHelper(this);
    private TaskRepository taskRepository;
    private RecyclerView vRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.taskRepository = TaskRepository.getInstance(dataBaseHelper);

//        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();
//        insert(writableDatabase);
//        writableDatabase.close();

        //clearDB();

        vRecView = findViewById(R.id.act1_rec_view);
        vRecView.setAdapter(new RecAdapter(taskRepository));
        vRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void clearDB() {
        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();
        writableDatabase.execSQL("delete from TASK");
        writableDatabase.close();
    }

    private void insert(SQLiteDatabase db) {
        insertDemoTasks(db, "task1", "task1 descr", new java.util.Date());
        insertDemoTasks(db, "task2", "task2 descr", new java.util.Date());
    }

    private void insertDemoTasks(SQLiteDatabase db, String title, String description, java.util.Date date) {
        ContentValues taskValues = new ContentValues();
        taskValues.put("TITLE", title);
        taskValues.put("DESCRIPTION", description);
        // need JDBC date escape format yyyy-[m]m-[d]d
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        taskValues.put("DATE", DATE_FORMAT.format(date));

        db.insert("TASK", null, taskValues);
    }

    public void removeTask(int taskId, int position) {
        taskRepository.removeTask(taskId);

        if (vRecView.getAdapter() != null) {
            vRecView.getAdapter().notifyItemRemoved(position);
        }
    }
}

class RecAdapter extends RecyclerView.Adapter<RecHolder> {

    private final TaskRepository taskRepository;

    public RecAdapter(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new RecHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder holder, int position) {
        holder.bind(this.taskRepository.getTask(position));
    }

    @Override
    public int getItemCount() {
        return taskRepository.getTasksCount();
    }
}

class RecHolder extends RecyclerView.ViewHolder {

    public RecHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(Task task) {
        CardView cardView = itemView.findViewById(R.id.card_view);
        cardView.setOnClickListener(v -> {
            // show Toast with detail
            Toast.makeText(
                    itemView.getContext(),
                    task.getFormattedDate() + ": " + task.getTitle() + ". " + task.getDescription(),
                    Toast.LENGTH_LONG
            ).show();
        });

        TextView title = itemView.findViewById(R.id.item_title);
        title.setText(task.getTitle());

        TextView date = itemView.findViewById(R.id.item_date);
        date.setText(task.getFormattedDate());

        CheckBox cb = itemView.findViewById(R.id.cb_task);
        cb.setOnCheckedChangeListener((buttonView, isChecked) ->
                ((MainActivity) cardView.getContext()).removeTask(task.getId(), getAdapterPosition()));
    }
}