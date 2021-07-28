package com.unsplashapp.domain.commands

import com.unsplashapp.domain.datasource.PhotosProvider
import com.unsplashapp.domain.model.PhotosDataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RequestPhotosCommand(private val numberOfPage: Int,
                           private val numberPerPage: Int,
                           private val orderBy: String,
                           private val photosProvider: PhotosProvider = PhotosProvider()):
    Command<PhotosDataResult?> {

    override suspend fun execute() = withContext(Dispatchers.IO) {
        photosProvider.requestPhotos(numberOfPage, numberPerPage, orderBy)
    }
}