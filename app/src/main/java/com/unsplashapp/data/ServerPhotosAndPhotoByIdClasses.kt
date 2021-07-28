package com.unsplashapp.data

data class Photos(val id: String, val likes: Int, val description: String?, val exif: Camera?,
                  val location: Location?, val tags: List<Tags>?, val user: User, val urls: Urls)

data class Camera(val make: String?, val model: String?, val exposure_time: String?,
                  val aperture: String?, val focal_length: String?, val iso: Int?)

data class Location(val city: String?, val country: String?)

data class Tags(val title: String)

data class User(val username: String, val name: String)

data class Urls(val small: String, val full: String)