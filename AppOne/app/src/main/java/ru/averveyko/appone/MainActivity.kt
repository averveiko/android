package ru.averveyko.appone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var vRecView: RecyclerView
    var request: Disposable? = null

    // Создание активити - тут обычно рисуют UI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vRecView = findViewById(R.id.act1_recView)

        // Обращение в сеть в отдельном потоке
        val o =
            createRequest("https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Ffeeds.bbci.co.uk%2Fnews%2Frss.xml")
                .map {
                    // Преобразовать json в объект
                    Gson().fromJson(it, Feed::class.java)
                }
                // Выполнить в потоке io
                .subscribeOn(Schedulers.io())
                // Результат обработать в главном UI потоке
                .observeOn(AndroidSchedulers.mainThread())

        // Запустить выполнение
        request = o.subscribe({
            // Если все ок, в этой лямбде будут обработаны результаты
            for (item in it.items) {
                // Вывод в лог
                Log.v("tag", "title: ${item.title}")
            }
            // Вывести список на вьюху
            //showLinearLayout(it.items)
            showRecView(it.items)
        }, {
            // Если произошла ошибка будет выполнена эта лямбда
            Log.e("tag", "", it)
        })
    }

    //val vTextView = findViewById<TextView>(R.id.act1_text)
    //vTextView.setOnClickListener {
    /*Log.v("tag", "message")
    // Запуск второй активити
    val intent = Intent(this, SecondActivity::class.java);
    intent.putExtra("txt", vTextView.text)
    startActivityForResult(intent, 0);
}*/
    /*fun showListView(feedList: List<FeedItem>) {
        val adapter = Adapter(feedList)
        vRecView.adapter = adapter
    }*/

    fun showRecView(feedList: List<FeedItem>) {
        vRecView.adapter = RecAdapter(feedList)
        // Нужно явно указать как отображать RecyclerView
        vRecView.layoutManager = LinearLayoutManager(this)
        // Добавить красивости к айтему
        //vRecView.addItemDecoration()
        // Анимации
        //vRecView.animation
    }

    // Показать фиды в виде списка на вьюхе
    /*fun showLinearLayout(feedList: List<FeedItem>) {
        val inflater = layoutInflater

        for (feedItem in feedList) {
            val view = inflater.inflate(R.layout.list_item, vList, false)
            val title = view.findViewById<TextView>(R.id.item_title)
            title.setText(feedItem.title)
            vList.addView(view)
        }
    }*/

    // Получение результата из вызванной дочерней активити
    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            val text = data.getStringExtra("txt")
            // или сделать переменной класса, чтобы повторно не искать
            val vTextView = findViewById<TextView>(R.id.act1_text)
            vTextView.text = text
        }
    }*/

    // Экран стал виден пользователю. Не нужен особо.
// Только для случая с разделением экрана на 2 приложения.
    override fun onStart() {
        super.onStart()
    }

    // Экран стал активен. Он работает.
// Тут можно запускать анимацию, все внутренние процессы
    override fun onResume() {
        super.onResume()
    }

    // Юзер прекратил работу с экраном - нажал home, back или зазвонит телефон
// Надо остановить все что мы запустили в onResume. Также после этого система может молча убить
// все приложение
    override fun onPause() {
        super.onPause()
    }

    // Симметричный к onStart, редко используется, т.к. есть onPause
    override fun onStop() {
        super.onStop()
    }

/*
 * После того, как юзер ответил на звонок, вернулся к нашему приложению, будет снова выполнены:
 * onStart, onResume .. Также есть бесполезный onRestart
 */

    // Последнее что может быть вызвано у Активити
    override fun onDestroy() {
        // Прервать выполнение запроса в сеть (для освобождения памяти)
        request?.dispose()
        super.onDestroy()
    }
}

class RecAdapter(val items: List<FeedItem>): RecyclerView.Adapter<RecHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {
        val inflater = LayoutInflater.from(parent!!.context)

        val view = inflater.inflate(R.layout.list_item, parent, false)

        return RecHolder(view)
    }

    override fun onBindViewHolder(holder: RecHolder, position: Int) {
        val item = items[position]
        holder?.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class RecHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bind(item: FeedItem) {
        val title = itemView.findViewById<TextView>(R.id.item_title)
        val desc = itemView.findViewById<TextView>(R.id.item_desc)
        val thumb = itemView.findViewById<ImageView>(R.id.item_thumb)
        title.text = item.title;
        desc.text = item.description;

        // Сейчас фид возвращает пустые item.thumbnail, используем рандомную демо-картинку
        val demoURL = "https://images.chesscomfiles.com/uploads/v1/user/19137822.b588af1e.1200x1200o.071ba55cf9bc.jpeg"
        Picasso.get().load(demoURL).into(thumb)

        // Добавим обработку нажатия
        itemView.setOnClickListener {
            // Открыть в бразуере
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(item.link)
            // У нас нет активити для просмотра URI, попросим систему найти такой
            thumb.context.startActivity(intent)
        }
    }
}

/*class Adapter(val items: List<FeedItem>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val inflater = LayoutInflater.from(parent!!.context)

        // Для оптимизации convertView на второй и далее итерации будет содержать уже созданную вью для переиспользования
        val view = convertView ?: inflater.inflate(R.layout.list_item, parent, false)

        val title = view.findViewById<TextView>(R.id.item_title)
        val item = getItem(position) as FeedItem
        title.setText(item.title)

        return view
    }
}*/

class Feed(
    val items: ArrayList<FeedItem>
)

class FeedItem(
    val title: String,
    val link: String,
    val thumbnail: String,
    val description: String
)

/*
"items": [
{
"title": "US Election 2020: Tense wait as US election winner remains unclear",
"pubDate": "2020-11-04 14:28:48",
"link": "https://www.bbc.co.uk/news/election-us-2020-54791113",
"guid": "https://www.bbc.co.uk/news/election-us-2020-54791113",
"author": "",
"thumbnail": "",
"description": "With millions of ballots yet to be counted, the focus is on Midwestern states like Michigan and Wisconsin.",
"content": "With millions of ballots yet to be counted, the focus is on Midwestern states like Michigan and Wisconsin.",
"enclosure": {},
"categories": []
},
 */