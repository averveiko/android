package ru.averveyko.messagemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMsg = findViewById(R.id.act1_list_view_msg);

        //Создаем демо-записи
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Маргарита", "Это водка?", getColor(R.color.teal_200)));
        messages.add(new Message("Бегемот", "Помилуйте, королева, разве я позволил" +
                " бы себе налить даме водки? Это чистый спирт!", getColor(R.color.purple_200)));
        messages.add(new Message("Воланд", "Аннушка уже купила подсолнечное масло," +
                " и не только купила, но даже разлила. Так что заседание не состоится. Далее " +
                "очень длинный текст, выходящий за пределы экрана", getColor(R.color.teal_700)));
        // Отображаем записи
        showMessages(messages);
    }

    /**
     * Отобразить список сообщений
     */
    private void showMessages(List<Message> messages) {
        Adapter adapter = new Adapter(messages);
        listViewMsg.setAdapter(adapter);
    }
}

/**
 * Адаптер для ListView
 */
class Adapter extends BaseAdapter {

    private List<Message> messages;

    public Adapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View msgView = convertView != null ?
                convertView :
                inflater.inflate(R.layout.list_item_msg, parent, false);

        TextView author = msgView.findViewById(R.id.item_msg_author);
        TextView text = msgView.findViewById(R.id.item_msg_text);

        Message msg = (Message) getItem(position);
        author.setText(msg.getAuthor());
        text.setText(msg.getText());
        msgView.setBackgroundColor(msg.getColor());

        Log.v("keash", "set bg color to " + msg.getColor());
        return msgView;
    }
}