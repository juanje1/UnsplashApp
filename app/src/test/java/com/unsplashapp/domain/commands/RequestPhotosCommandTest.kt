package com.unsplashapp.domain.commands

import com.unsplashapp.domain.datasource.PhotosProvider
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RequestPhotosCommandTest {

    @Test
    fun `provider is called when command is executed`() {
        val photoProvider = mock(PhotosProvider::class.java)
        val command = RequestPhotosCommand(1, 10, "latest", photoProvider)

        runBlocking { command.execute() }

        verify(photoProvider).requestPhotos(1, 10, "latest")
    }
}