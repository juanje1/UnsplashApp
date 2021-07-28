package com.unsplashapp.domain.model

data class PhotoByIdResult(val likes: Int, val description: String?, val exif: CameraByIdResult?,
                           val location: LocationByIdResult?, val tags: List<TagsByIdResult>?,
                           val user: UserByIdResult, val urls: UrlsByIdResult)

data class CameraByIdResult(val make: String?, val model: String?, val exposure_time: String?,
                            val aperture: String?, val focal_length: String?, val iso: Int?)

data class LocationByIdResult(val city: String?, val country: String?)

data class TagsByIdResult(val title: String)

data class UserByIdResult(val username: String, val name: String)

data class UrlsByIdResult(val small: String, val full: String)