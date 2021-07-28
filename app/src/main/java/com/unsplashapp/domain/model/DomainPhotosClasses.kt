package com.unsplashapp.domain.model

data class PhotosDataResult(val photosList: List<PhotosResult>) {
    val size: Int
        get() = photosList.size
    operator fun get(position: Int) = photosList[position]
}

data class PhotosResult(val id: String, val likes: Int, val user: UserResult, val urls: UrlsResult)

data class UserResult(val username: String, val name: String)

data class UrlsResult(val small: String)