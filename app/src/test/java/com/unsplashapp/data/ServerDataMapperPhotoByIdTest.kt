package com.unsplashapp.data

import com.unsplashapp.domain.model.*
import com.unsplashapp.extensions.getPhotos
import org.junit.Assert
import org.junit.Test

class ServerDataMapperPhotoByIdTest {

    @Test
    fun `ServerDataMapper convert List of Photos to PhotoByIdResult`() {
        val serverDataMapperPhotoById = ServerDataMapperPhotoById()
        val photoByIdResult = serverDataMapperPhotoById.convertToDomain(getPhotos(1))

        Assert.assertEquals(getPhotoByIdResult(), photoByIdResult)
    }

    private fun getPhotoByIdResult(): PhotoByIdResult {
        val id = "1"
        val imageSmallById = "/img/src/picture${id}.jpg"

        val tags1ById = TagsByIdResult("title 1")
        val tags2ById = TagsByIdResult("title 2")

        val exifById = CameraByIdResult("XXX", "XXX-123", "1/100", "1.0", "2.0", 100)
        val locationById = LocationByIdResult("City $id", "Country $id")
        val tagsById = arrayListOf(tags1ById, tags2ById)
        val userById = UserByIdResult("UserName $id", "Name $id")
        val urlsById = UrlsByIdResult(imageSmallById)

        return PhotoByIdResult(1, "description $id", exifById, locationById, tagsById, userById, urlsById)
    }
}