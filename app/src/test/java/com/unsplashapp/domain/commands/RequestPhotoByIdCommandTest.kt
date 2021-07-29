package com.unsplashapp.domain.commands

import com.unsplashapp.domain.datasource.PhotosProvider
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RequestPhotoByIdCommandTest {

    @Test
    fun `provider is called when command is executed`() {
        val photoProvider = mock(PhotosProvider::class.java)
        val command = RequestPhotoByIdCommand("id", photoProvider)

        runBlocking { command.execute() }

        verify(photoProvider).requestPhotoById("id")
    }
}