package ru.averveyko.appone

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import androidx.core.app.NotificationCompat

/*
    Сервис работает в том же потоке, поэтому все долгоиграющее надо запускать в новых потоках
 */
class PlayService: Service() {

    var player: MediaPlayer? = null
    var notification: NotificationCompat.Builder? = null

    // Будет вызван один раз при создании сервиса
    override fun onCreate() {
        super.onCreate()
    }

    // Тут мы должны что-то запустить и выйти
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // Если в нотификации нажали "стоп"
        if (intent?.action == "stop") {
            player?.stop()
            // Выключаем нотификацию
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).cancel(333)
            // Останавливаем сервис
            stopSelf()
            return START_NOT_STICKY
        }

        player?.stop()

        val url = intent!!.extras?.getString("mp3")
        player = MediaPlayer()
        player?.setDataSource(this, Uri.parse(url))
        player?.setOnPreparedListener {
            it.start()
        }
        player?.prepareAsync()

        val notificationIntent = Intent(
            this,
            MainActivity::class.java
        ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val iStop = Intent(this, PlayService::class.java).setAction("stop")
        val piStop = PendingIntent.getService(this, 0, iStop, PendingIntent.FLAG_CANCEL_CURRENT)

        // Похоже для новой версии андроида нужно что-то доработать, этот вариант не работает
        // Если что, тут еще прогрессбар можно сделать, см https://github.com/VBorodinTT/AndroidLectures/blob/master/Lecture7/src/main/java/android/lectures/PlayService.kt
        notification = NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("MP3")
            .setContentText("")
            .setContentIntent(PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT))
            .addAction(R.mipmap.ic_launcher, "Stop", piStop)
            .setAutoCancel(true)
            .setOngoing(false)

        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(333, notification?.build())


        // Какого типа сервис мы хотим запустить
        // START_STICKY - вслучае убийства система должна его перезапустить
        // START_NOT_STICKY - убили и ладно
        return START_NOT_STICKY
    }

    // Тут освобождаем ресурсы
    override fun onDestroy() {
        player?.stop()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}