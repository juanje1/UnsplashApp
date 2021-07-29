package com.unsplashapp.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.unsplashapp.R
import com.unsplashapp.extensions.*
import com.squareup.picasso.Picasso
import com.unsplashapp.domain.commands.RequestPhotoByIdCommand
import com.unsplashapp.domain.model.PhotoByIdResult
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.launch
import org.jetbrains.anko.find
import java.lang.StringBuilder

class DetailActivity : CoroutineScopeActivity(), ToolbarManager {

    private var updatedSettingsCurrent: Boolean by DelegatesExt.preference(
        this, SettingsActivity.UPDATED_SETTINGS, SettingsActivity.DEFAULT_UPDATED_SETTINGS)

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    companion object {
        const val PHOTO_ID = "DetailActivity:photoId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initToolbar()
        enableHomeAsUp { onBackPressed() }
        loadPhotoById()
    }

    override fun onResume() {
        super.onResume()

        if (updatedSettingsCurrent) {
            loadPhotoById()
        }
    }

    private fun loadPhotoById() = launch {
        val photoId = intent.getStringExtra(PHOTO_ID)
        val result = RequestPhotoByIdCommand(photoId!!).execute()

        if (result != null) {
            bindPhotoById(result)
        } else showErrorAndRetry(findViewById(R.id.scrollView))
    }

    private fun showErrorAndRetry(view: View) {
        Snackbar.make(view, MESSAGE_ERROR, Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") { loadPhotoById() }
            .setActionTextColor(Color.RED)
            .show()
    }

    private fun bindPhotoById(photo: PhotoByIdResult) = with(photo) {
        toolbarTitle = user.username
        Picasso.with(this@DetailActivity).load(urls.small).into(photoIcon)
        userNameText.text = user.username
        photoDescription.text = getString(R.string.photoDescription, isNull(description))
        photoLikes.text = getString(R.string.photoLikes, likes.toString())

        photoCamera.text = Html.fromHtml(this@DetailActivity.getString(R.string.photoCamera), 1)
        make.text = getString(R.string.make, isNull(exif?.make))
        model.text = getString(R.string.model, isNull(exif?.model))
        exposureTime.text = getString(R.string.exposureTime, isNull(exif?.exposure_time))
        aperture.text = getString(R.string.aperture, isNull(exif?.aperture))
        focalLength.text = getString(R.string.focalLength, isNull(exif?.focal_length))
        iso.text = getString(R.string.iso, isNull(exif?.iso?.toString()))

        photoLocation.text = Html.fromHtml(this@DetailActivity.getString(R.string.photoLocation), 1)
        city.text = getString(R.string.city, isNull(location?.city))
        country.text = getString(R.string.country, isNull(location?.country))

        photoTags.text = Html.fromHtml(this@DetailActivity.getString(R.string.photoTags), 1)
        val tagsStringBuilder = StringBuilder()
        isNull(tags?.forEach { tagsStringBuilder.append("${it.title}\n") } )
        tagsList.text = tagsStringBuilder

        photoUser.text = Html.fromHtml(this@DetailActivity.getString(R.string.photoUser), 1)
        name.text = getString(R.string.photoName, user.name)
        userName.text = getString(R.string.photoUserName, user.username)
    }

    private fun <T> isNull(t: T?) = t ?: FIELD_NULL
}