package com.unsplashapp.ui.activities

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.unsplashapp.R
import com.unsplashapp.domain.commands.RequestPhotosCommand
import com.unsplashapp.extensions.*
import com.unsplashapp.ui.adapters.PhotosListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MainActivity : CoroutineScopeActivity(), ToolbarManager {

    private val numberOfPageCurrent: Int by DelegatesExt.preference(
        this, SettingsActivity.NUMBER_OF_PAGE, SettingsActivity.DEFAULT_NUMBER_OF_PAGE)

    private val numberPerPageCurrent: Int by DelegatesExt.preference(
        this, SettingsActivity.NUMBER_PER_PAGE, SettingsActivity.DEFAULT_NUMBER_PER_PAGE)

    private var updatedSettingsCurrent: Boolean by DelegatesExt.preference(
        this, SettingsActivity.UPDATED_SETTINGS, SettingsActivity.DEFAULT_UPDATED_SETTINGS)

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        initToolbar()

        photosList.layoutManager = LinearLayoutManager(this)
        attachToScroll(photosList)
        loadPhotos()
    }

    override fun onResume() {
        super.onResume()

        if (updatedSettingsCurrent) {
            loadPhotos()
            updatedSettingsCurrent = false
        }
    }

    private fun loadPhotos() = launch {
        val prefs: SharedPreferences = getSharedPreferences("default", Context.MODE_PRIVATE)
        val orderCurrent: String? = prefs.getString(SettingsActivity.ORDER, SettingsActivity.DEFAULT_ORDER)

        val result = RequestPhotosCommand(numberOfPageCurrent, numberPerPageCurrent, orderCurrent!!)
            .execute()

        if (result != null) {
            val adapter = PhotosListAdapter(result) {
                startActivity<DetailActivity>(DetailActivity.PHOTO_ID to it.id)
            }
            photosList.adapter = adapter
            toolbarTitle = "${getString(R.string.toolbarIndex)} ${getCurrentDate()}"
        } else showErrorAndRetry(findViewById(R.id.frameLayout))
    }

    private fun showErrorAndRetry(view: View) {
        Snackbar.make(view, MESSAGE_ERROR, Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry"){ loadPhotos() }
            .setActionTextColor(Color.RED)
            .show()
    }
}