package com.unsplashapp.data

import android.util.Log
import com.unsplashapp.extensions.*

class PhotosRequest(private val numberOfPage: Int,
                    private val numberPerPage: Int,
                    private val orderBy: String) {

    fun execute(): List<Photos>? {
        val url = "${URL_PHOTOS}?page=${numberOfPage}&per_page=${numberPerPage}&orderBy=${orderBy}&client_id=${ACCESS_KEY}"
        Log.d("Url", url)
        return executeUrlPhotos(url)
    }
}