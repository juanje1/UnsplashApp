package com.unsplashapp.data

import android.util.Log
import com.unsplashapp.extensions.*

class PhotoByIdRequest(private val photoId: String) {

    fun execute(): Photos? {
        val url = "${URL_PHOTO_BY_ID}${photoId}?client_id=${ACCESS_KEY}"
        Log.d("Url", url)
        return executeUrlPhotoById(url)
    }
}