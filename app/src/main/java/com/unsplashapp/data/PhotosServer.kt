package com.unsplashapp.data

import com.unsplashapp.domain.datasource.PhotosDataSource
import com.unsplashapp.domain.model.PhotoByIdResult
import com.unsplashapp.domain.model.PhotosDataResult

class PhotosServer(private val dataMapperPhotos: ServerDataMapperPhotos = ServerDataMapperPhotos(),
                   private val dataMapperPhotoById: ServerDataMapperPhotoById = ServerDataMapperPhotoById())
    : PhotosDataSource {

    override fun requestPhotos(numberOfPage: Int, numberPerPage: Int, orderBy: String): PhotosDataResult? {
        val result = PhotosRequest(numberOfPage, numberPerPage, orderBy).execute()
        return result?.let { dataMapperPhotos.convertToDomain(it) }
    }

    override fun requestPhotoById(photoId: String): PhotoByIdResult? {
        val result = PhotoByIdRequest(photoId).execute()
        return result?.let { dataMapperPhotoById.convertToDomain(it) }
    }
}