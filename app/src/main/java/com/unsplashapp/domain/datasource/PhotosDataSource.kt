package com.unsplashapp.domain.datasource

import com.unsplashapp.domain.model.PhotoByIdResult
import com.unsplashapp.domain.model.PhotosDataResult

interface PhotosDataSource {

    fun requestPhotos(numberOfPage: Int, numberPerPage: Int, orderBy: String): PhotosDataResult?

    fun requestPhotoById(photoId: String): PhotoByIdResult?

}