package com.example.uniscope.data.remote.jsoup

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

class JsoupWrapper @Inject constructor() {
    fun connect(url: String): Document = Jsoup.connect(url).get()
}