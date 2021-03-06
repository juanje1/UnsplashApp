package com.unsplashapp.domain.commands

interface Command<out T> {
    suspend fun execute(): T
}