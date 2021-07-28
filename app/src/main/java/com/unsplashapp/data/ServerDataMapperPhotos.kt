package com.unsplashapp.data

import com.unsplashapp.domain.model.*

class ServerDataMapperPhotos {

    fun convertToDomain(photosList: List<Photos>) =
        PhotosDataResult(convertPhotoListToDomain(photosList))

    private fun convertPhotoListToDomain(photosList: List<Photos>) =
        photosList.map { convertPhotoItemToDomain(it.copy()) }

    private fun convertPhotoItemToDomain(photo: Photos) = with(photo) {
        PhotosResult(id, likes, convertUserToDomain(user), convertUrlsToDomain(urls))
    }

    private fun convertUserToDomain(user: User) = with(user) {
        UserResult(username, name)
    }

    private fun convertUrlsToDomain(urls: Urls) = with(urls) {
        UrlsResult(small)
    }
}