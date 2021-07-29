package com.unsplashapp.data

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class PhotosServerTest {

    private val dataMapperPhotos = Mockito.mock(ServerDataMapperPhotos::class.java)
    private val dataMapperPhotoById = Mockito.mock(ServerDataMapperPhotoById::class.java)
    private val photoServer = PhotosServer(dataMapperPhotos, dataMapperPhotoById)

    @Test
    fun `dataMapperPhotos is called when result is not null`() {
        val result = PhotosRequest(1,10, "latest").execute()

        runBlocking { photoServer.requestPhotos(1, 10, "latest") }
        if (result != null) Mockito.verify(dataMapperPhotos).convertToDomain(result)
    }

    @Test
    fun `dataMapperPhotoById is called when result is not null`() {
        val resultPhotos = PhotosRequest(1, 10, "latest").execute()

        if (resultPhotos != null) {
            val id = resultPhotos[0].id
            val result = PhotoByIdRequest(id).execute()

            runBlocking { photoServer.requestPhotoById(id) }
            if (result != null) Mockito.verify(dataMapperPhotoById).convertToDomain(result)
        }
    }
}