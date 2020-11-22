package ru.averveyko.appone

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Создание активити - тут обычно рисуют UI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        // Если это восстановление свернутого окна (etc), то не надо делать лишние движения
        if (savedInstanceState == null) {

            // На случай, если что-то нужно передать создаваемому фрагменту
            val bundle = Bundle()
            bundle.putString("param", "value")
            val fragment = MainFragment();
            fragment.arguments = bundle

            this@MainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_place, fragment)
                .commitAllowingStateLoss()
        }
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
        super.onDestroy()
    }

    fun showArticle(url: String) {
        val bundle = Bundle()
        bundle.putString("url", url)
        val fragment = SecondFragment();
        fragment.arguments = bundle

        // frame2 будет присутствовать только в landscape режиме
        val frame2 = findViewById<View>(R.id.fragment_place2)
        if (frame2 != null) {
            frame2.visibility = View.VISIBLE
            this@MainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_place2, fragment)
                .commitAllowingStateLoss()
        } else {
            // Добавим фрагмент на вершину стека
            this@MainActivity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_place, fragment)
                .addToBackStack("main") // Это позволит обработать кнопку back
                .commitAllowingStateLoss()
        }
    }

    fun playMusic(guid: String) {
        // Запустить сервис и передать url
        val intent = Intent(this, PlayService::class.java)
        intent.putExtra("mp3", guid)
        startService(intent)
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