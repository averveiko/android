package ru.averveyko.messagemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMsg;
    private final List<Dialog> dialogs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMsg = findViewById(R.id.act1_list_view_msg);

        //Вставляем демо-записи в список записей
        addDemoMessages();
        // Отображаем записи
        showMessages(dialogs);
    }

    /**
     * Отобразить список дилогов
     */
    private void showMessages(List<Dialog> dialogs) {
        Adapter adapter = new Adapter(dialogs);
        listViewMsg.setAdapter(adapter);
    }

    /**
     * Добавляет демо-записи
     */
    private void addDemoMessages() {
        //android:src="@drawable/begemot"
        dialogs.add(new Dialog("Маргарита", "Это водка?",
                ContextCompat.getDrawable(this, R.drawable.margarita), getColor(R.color.teal_200)));
        dialogs.add(new Dialog("Бегемот", "Помилуйте, королева, разве я позволил" +
                " бы себе налить даме водки? Это чистый спирт!",
                ContextCompat.getDrawable(this, R.drawable.begemot), getColor(R.color.purple_200)));
        dialogs.add(new Dialog("Воланд", "Аннушка уже купила подсолнечное масло," +
                " и не только купила, но даже разлила. Так что заседание не состоится. Далее " +
                "длинный текст, выходящий за пределы экрана",
                ContextCompat.getDrawable(this, R.drawable.voland), getColor(R.color.teal_700)));
    }
}

/**
 * Адаптер для ListView
 */
class Adapter extends BaseAdapter {

    private List<Dialog> dialogs;

    public Adapter(List<Dialog> dialogs) {
        this.dialogs = dialogs;
    }

    @Override
    public int getCount() {
        return dialogs.size();
    }

    @Override
    public Object getItem(int position) {
        return dialogs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View dialogView = convertView != null ?
                convertView :
                inflater.inflate(R.layout.list_item_msg, parent, false);

        TextView author = dialogView.findViewById(R.id.item_msg_author);
        TextView text = dialogView.findViewById(R.id.item_msg_text);
        ImageView avatar = dialogView.findViewById(R.id.img_avatar);

        Dialog dialog = (Dialog) getItem(position);
        author.setText(dialog.getAuthor());
        text.setText(dialog.getText());
        avatar.setImageDrawable(dialog.getAvatar());
        dialogView.setBackgroundColor(dialog.getColor());

        return dialogView;
    }
}