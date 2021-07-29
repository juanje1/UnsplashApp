package com.unsplashapp.domain.datasource

import com.unsplashapp.data.PhotosServer
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class PhotoProviderTest {

    private val source = mock(PhotosServer::class.java)
    private val provider = PhotosProvider(source)

    @Test
    fun `data source is called when requestPhotos is executed`() {
        runBlocking { provider.requestPhotos(1, 10, "latest") }
        verify(source).requestPhotos(1, 10, "latest")
    }

    @Test
    fun `data source is called when requestPhotoById is executed`() {
        runBlocking { provider.requestPhotoById("id") }
        verify(source).requestPhotoById("id")
    }
}