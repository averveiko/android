package ru.averveyko.appone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject

class MainFragment : Fragment() {

    lateinit var vRecView: RecyclerView
    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Достать то, что передали из MainActivity
        val params = arguments?.getString("param")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.activity_main, container, false)

        vRecView = view.findViewById(R.id.act1_recView)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Обращение в сеть в отдельном потоке
        val o =
            createRequest("https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Ffeeds.bbci.co.uk%2Fnews%2Frss.xml")
                .map {
                    // Преобразовать json в объект
                    Gson().fromJson(it, FeedApi::class.java)
                }
                // Выполнить в потоке io
                .subscribeOn(Schedulers.io())
                // Результат обработать в главном UI потоке
                .observeOn(AndroidSchedulers.mainThread())

        // Запустить выполнение
        request = o.subscribe({
            // Если все ок, в этой лямбде будут обработаны результаты

            val feed = Feed(
                it.items
                    .mapTo(
                        RealmList<FeedItem>(),
                        { feed ->
                            FeedItem(
                                feed.title,
                                feed.link,
                                feed.thumbnail,
                                feed.description
                            )
                        }
                    ))

            // Сохраняем в базу весь полученный feed
            Realm.getDefaultInstance().executeTransaction { realm ->

                // Для начала удалим старое
                val oldList = realm.where(Feed::class.java).findAll()
                if (oldList.size > 0) {
                    for (item in oldList) {
                        item.deleteFromRealm()
                    }
                }
                // теперь запишем новое
                realm.copyToRealm(feed)
            }
            // Вывести список на вьюху
            //showLinearLayout(it.items)
            showRecView()

        }, {
            // Если произошла ошибка будет выполнена эта лямбда
            Log.e("tag", "", it)
            // Если пропал например интернет - попробуем из базы загрузить
            showRecView()
        })
    }

    fun showRecView() {
        Realm.getDefaultInstance().executeTransaction { realm ->
            // Может быть ситуация что activity == null
            if (!isVisible) {
                return@executeTransaction
            }
            val feedList = realm.where(Feed::class.java).findAll()
            if (feedList.size > 0) {
                vRecView.adapter = RecAdapter(feedList[0]!!.items)
                // Нужно явно указать как отображать RecyclerView
                vRecView.layoutManager = LinearLayoutManager(activity)
                // Добавить красивости к айтему
                //vRecView.addItemDecoration()
                // Анимации
                //vRecView.animation
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Прервать выполнение запроса в сеть (для освобождения памяти)
        request?.dispose()
    }
}

class RecAdapter(val itemApis: List<FeedItem>) : RecyclerView.Adapter<RecHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {
        val inflater = LayoutInflater.from(parent!!.context)

        val view = inflater.inflate(R.layout.list_item, parent, false)

        return RecHolder(view)
    }

    override fun onBindViewHolder(holder: RecHolder, position: Int) {
        val item = itemApis[position]
        holder?.bind(item)
    }

    override fun getItemCount(): Int {
        return itemApis.size
    }

}

class RecHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(itemApi: FeedItem) {
        val title = itemView.findViewById<TextView>(R.id.item_title)
        val desc = itemView.findViewById<TextView>(R.id.item_desc)
        val thumb = itemView.findViewById<ImageView>(R.id.item_thumb)
        title.text = itemApi.title;
        desc.text = itemApi.description;

        // Сейчас фид возвращает пустые item.thumbnail, используем рандомную демо-картинку
        val demoURL =
            "https://images.chesscomfiles.com/uploads/v1/user/19137822.b588af1e.1200x1200o.071ba55cf9bc.jpeg"
        Picasso.get().load(demoURL).into(thumb)

        // Добавим обработку нажатия
        /*itemView.setOnClickListener {
            // Открыть в бразуере
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(itemApi.link)
            // У нас нет активити для просмотра URI, попросим систему найти такой
            thumb.context.startActivity(intent)
        }*/

        itemView.setOnClickListener {
            (thumb.context as MainActivity).showArticle(itemApi.link)
        }
    }
}

// Классы для загрузки из API
class FeedApi(
    val items: ArrayList<FeedItemApi>
)

class FeedItemApi(
    val title: String,
    val link: String,
    val thumbnail: String,
    val description: String
)

// Классы для сохранения в БД
open class Feed(
    var items: RealmList<FeedItem> = RealmList<FeedItem>()
) : RealmObject()

open class FeedItem(
    var title: String = "",
    var link: String = "",
    var thumbnail: String = "",
    var description: String = ""
) : RealmObject()