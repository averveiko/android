package ru.averveyko.appone

import io.reactivex.Observable
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL

/**
 * Запрос в сеть
 */
fun createRequest(url: String) = Observable.create<String> {
    val urlConnection = URL(url).openConnection() as HttpURLConnection
    try {
        urlConnection.connect()

        if (urlConnection.responseCode != HttpURLConnection.HTTP_OK) {
            it.onError(RuntimeException(urlConnection.responseMessage))
        } else {
            val str = urlConnection.inputStream.bufferedReader().readText()
            it.onNext(str)
        }
    } finally {
        urlConnection.disconnect();
    }
}
