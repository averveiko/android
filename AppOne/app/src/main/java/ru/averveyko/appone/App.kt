package ru.averveyko.appone

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    /*
     Запуск приложения. Тут можно инициализировать какие-то классы которые нужны на всем протяжении
     жизни приложения: реклама, и т.д.
     */
    override fun onCreate() {
        super.onCreate()

        // Инициализируем Realm
        Realm.init(this)

        val realmConfig = RealmConfiguration.Builder()
            .schemaVersion(1)
             // удалить данные если структура изменилась и нужна миграция
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(realmConfig)
    }
}