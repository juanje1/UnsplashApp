package com.unsplashapp.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.unsplashapp.data.*
import java.io.FileNotFoundException
import java.net.URL
import java.text.DateFormat
import java.util.*

const val ACCESS_KEY = "GYefT6RhpxgOyu8jW5pulluXnXKiM4gD6WScqhc6GXM"
const val SERVER = "https://api.unsplash.com"
const val ENDPOINT_PHOTOS = "/photos"
const val URL_PHOTOS = "${SERVER}${ENDPOINT_PHOTOS}"
const val URL_PHOTO_BY_ID = "${SERVER}${ENDPOINT_PHOTOS}/"
const val MESSAGE_ERROR = "Internal Error in Unsplash App"
const val FIELD_NULL = "Unknown"

private val gson = Gson()
private var outputJsonStr: String ?= null

fun getCurrentDate(): String {
    val df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
    return df.format(Date())
}

fun executeUrlPhotos(url: String): List<Photos>? {
    outputJsonStr = null
    try { outputJsonStr = URL(url).readText() }
    catch (exception: FileNotFoundException) { }
    finally { return getGsonPhotos() }
}

private fun getGsonPhotos(): List<Photos>? {
    val listType = object : TypeToken<List<Photos>>() {}.type
    return gson.fromJson<List<Photos>>(outputJsonStr, listType)
}

fun executeUrlPhotoById(url: String): Photos? {
    outputJsonStr = null
    try { outputJsonStr = URL(url).readText() }
    catch (exception: FileNotFoundException) { }
    finally { return getGsonPhotoById() }
}

private fun getGsonPhotoById(): Photos? =
    gson.fromJson(outputJsonStr, Photos::class.java)


fun getListPhotos(numberElements: Int): List<Photos> {
    val photosList = ArrayList<Photos>()
    for (i in 1..numberElements) photosList.add(getPhotos(i))
    return photosList
}

fun getPhotos(id: Int): Photos {
    val imageSmall = "/img/src/picture${id}.jpg"

    val tags1 = Tags("title 1")
    val tags2 = Tags("title 2")

    val exif = Camera("XXX", "XXX-123", "1/100", "1.0", "2.0", 100)
    val location = Location("City $id", "Country $id")
    val tags = arrayListOf(tags1, tags2)
    val user = User("UserName $id", "Name $id")
    val urls = Urls(imageSmall)

    return Photos(id.toString(), 1, "description $id", exif, location, tags, user, urls)
}