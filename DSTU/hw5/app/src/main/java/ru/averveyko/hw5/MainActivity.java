package ru.averveyko.hw5;

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

import java.util.List;

import ru.averveyko.hw5.domain.Task;
import ru.averveyko.hw5.repository.TaskRepository;

public class MainActivity extends AppCompatActivity {

    private final TaskRepository taskRepository = TaskRepository.getInstance();
    private RecyclerView vRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vRecView = findViewById(R.id.act1_rec_view);
        vRecView.setAdapter(new RecAdapter(taskRepository.getTasks()));
        vRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    public boolean removeTask(Task task, int position) {
        if (taskRepository.removeTask(task)) {
            vRecView.getAdapter().notifyItemRemoved(position);
            return true;
        }
        return false;
    }
}

class RecAdapter extends RecyclerView.Adapter<RecHolder> {

    private List<Task> tasks;

    public RecAdapter(List<Task> tasks) {
        this.tasks = tasks;
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
        holder.bind(this.tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
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
        cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            ((MainActivity)cardView.getContext()).removeTask(task, getAdapterPosition());
        });
    }
}