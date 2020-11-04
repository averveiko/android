package ru.averveyko.appone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    var request: Disposable? = null

    // Создание активити - тут обычно рисуют UI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vTextView = findViewById<TextView>(R.id.act1_text)
        vTextView.setOnClickListener {
            /*Log.v("tag", "message")
            // Запуск второй активити
            val intent = Intent(this, SecondActivity::class.java);
            intent.putExtra("txt", vTextView.text)
            startActivityForResult(intent, 0);*/

            // Обращение в сеть в отдельном потоке
            val o = createRequest("https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Ffeeds.bbci.co.uk%2Fnews%2Frss.xml")
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
                    Log.v("tag", "title: ${item.title}")
                }
            }, {
                // Если произошла ошибка будет выполнена эта лямбда
                Log.e("tag","", it)
            })
        }
    }

    // Получение результата из вызванной дочерней активити
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            val text = data.getStringExtra("txt")
            // или сделать переменной класса, чтобы повторно не искать
            val vTextView = findViewById<TextView>(R.id.act1_text)
            vTextView.text = text
        }
    }

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

class Feed(
    val items:ArrayList<FeedItem>
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