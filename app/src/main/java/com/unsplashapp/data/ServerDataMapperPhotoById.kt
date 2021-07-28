package com.unsplashapp.data

import com.unsplashapp.domain.model.*

class ServerDataMapperPhotoById {

    fun convertToDomain(photo: Photos) = with(photo) {
        PhotoByIdResult(likes, description, convertCameraByIdToDomain(exif),
            convertLocationByIdToDomain(location), convertTagsByIdListToDomain(tags),
            convertUserByIdToDomain(user), convertUrlsByIdToDomain(urls))
    }

    private fun convertCameraByIdToDomain(exif: Camera?) = with(exif) {
        CameraByIdResult(this?.make, this?.model, this?.exposure_time, this?.aperture,
            this?.focal_length, this?.iso)
    }

    private fun convertLocationByIdToDomain(location: Location?) = with(location) {
        LocationByIdResult(this?.city, this?.country)
    }

    private fun convertTagsByIdListToDomain(tagsList: List<Tags>?) =
        tagsList?.map { convertTagByIdItemToDomain(it.copy()) }

    private fun convertTagByIdItemToDomain(tag: Tags) = with(tag) {
        TagsByIdResult(title)
    }

    private fun convertUserByIdToDomain(user: User) = with(user) {
        UserByIdResult(username, name)
    }

    private fun convertUrlsByIdToDomain(urls: Urls) = with(urls) {
        UrlsByIdResult(small, full)
    }
}