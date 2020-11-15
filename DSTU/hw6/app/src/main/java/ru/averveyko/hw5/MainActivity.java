package ru.averveyko.hw5;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.averveyko.hw5.domain.Task;
import ru.averveyko.hw5.repository.TaskRepository;
import ru.averveyko.hw5.sqlite.TasksDataBaseHelper;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE = 0;
    public static final String LOG_TAG = "TASKMGR";
    public static final String EXTRA_DATE = "DATE";
    public static final String EXTRA_TITLE = "TITLE";
    public static final String EXTRA_DESCRIPTION = "DESCRIPTION";
    public static SimpleDateFormat HUMAN_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    private final SQLiteOpenHelper dataBaseHelper = new TasksDataBaseHelper(this);
    private TaskRepository taskRepository;
    private RecyclerView vRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.taskRepository = TaskRepository.getInstance(dataBaseHelper);

        FloatingActionButton fabAddTask = findViewById(R.id.fab_add_task);
        fabAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(fabAddTask.getContext(), AddTaskActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });

        vRecView = findViewById(R.id.act1_rec_view);
        vRecView.setAdapter(new RecAdapter(taskRepository));
        vRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Date date = null;
                    try {
                        date = HUMAN_DATE_FORMAT.parse(data.getStringExtra(EXTRA_DATE));
                    } catch (ParseException e) {
                        Log.e(LOG_TAG, "Некорректный формат даты (требуется dd.MM.yyyy)");
                    }

                    Task task = new Task(
                            0,
                            data.getStringExtra(EXTRA_TITLE),
                            data.getStringExtra(EXTRA_DESCRIPTION),
                            date
                    );
                    taskRepository.addTask(task);
                    if (vRecView.getAdapter() != null) {
                        vRecView.getAdapter().notifyDataSetChanged();
                    }
                }
            }
        }
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
                    task.getFormattedDate(MainActivity.HUMAN_DATE_FORMAT) + ": " + task.getTitle() + ". " + task.getDescription(),
                    Toast.LENGTH_LONG
            ).show();
        });

        TextView title = itemView.findViewById(R.id.item_title);
        title.setText(task.getTitle());

        TextView date = itemView.findViewById(R.id.item_date);
        date.setText(task.getFormattedDate(MainActivity.HUMAN_DATE_FORMAT));

        CheckBox cb = itemView.findViewById(R.id.cb_task);
        cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
                ((MainActivity) cardView.getContext()).removeTask(task.getId(), getAdapterPosition());
                cb.setChecked(false);
        });
    }
}