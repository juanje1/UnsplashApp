package com.unsplashapp.data

import com.unsplashapp.domain.model.*
import com.unsplashapp.extensions.getListPhotos
import org.junit.Assert.assertEquals
import org.junit.Test

class ServerDataMapperPhotosTest {

    @Test
    fun `ServerDataMapper convert List of Photos to PhotosDataResult`() {
        val serverDataMapperPhotos = ServerDataMapperPhotos()
        val photosDataResult = serverDataMapperPhotos.convertToDomain(getListPhotos(3))

        assertEquals(getPhotosDataResult(), photosDataResult)
    }

    private fun getPhotosDataResult(): PhotosDataResult {
        val photosResultList = ArrayList<PhotosResult>()
        for (i in 1..3) photosResultList.add(getPhotosResult(i.toString()))
        return PhotosDataResult(photosResultList)
    }

    private fun getPhotosResult(id: String): PhotosResult {
        val imageSmall = "/img/src/picture${id}.jpg"

        val userResult = UserResult("UserName $id", "Name $id")
        val urlsResult = UrlsResult(imageSmall)

        return PhotosResult(id, 1, userResult, urlsResult)
    }
}