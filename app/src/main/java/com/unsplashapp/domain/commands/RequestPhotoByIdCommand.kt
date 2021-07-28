package com.unsplashapp.domain.commands

import com.unsplashapp.domain.datasource.PhotosProvider
import com.unsplashapp.domain.model.PhotoByIdResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RequestPhotoByIdCommand(private val photoId: String,
                              private val photosProvider: PhotosProvider = PhotosProvider()):
    Command<PhotoByIdResult?> {

    override suspend fun execute() = withContext(Dispatchers.IO) {
        photosProvider.requestPhotoById(photoId)
    }
}