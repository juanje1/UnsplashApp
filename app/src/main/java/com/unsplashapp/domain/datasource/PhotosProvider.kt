package com.unsplashapp.domain.datasource

import com.unsplashapp.data.PhotosServer
import com.unsplashapp.domain.model.PhotoByIdResult
import com.unsplashapp.domain.model.PhotosDataResult

class PhotosProvider(private val source: PhotosDataSource = SOURCE) {

    companion object {
        val SOURCE by lazy { PhotosServer() }
    }

    fun requestPhotos(numberOfPage: Int, numberPerPage: Int, orderBy: String): PhotosDataResult? =
        source.requestPhotos(numberOfPage, numberPerPage, orderBy)

    fun requestPhotoById(photoId: String): PhotoByIdResult? =
        source.requestPhotoById(photoId)
}