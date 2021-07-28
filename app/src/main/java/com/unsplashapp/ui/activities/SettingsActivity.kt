package com.unsplashapp.ui.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.unsplashapp.R
import com.unsplashapp.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val NUMBER_OF_PAGE = "numberOfPage"
        const val DEFAULT_NUMBER_OF_PAGE = 1
        const val NUMBER_PER_PAGE = "numberPerPage"
        const val DEFAULT_NUMBER_PER_PAGE = 10
        const val ORDER = "order"
        const val DEFAULT_ORDER = "latest"
        const val UPDATED_SETTINGS = "updated"
        const val DEFAULT_UPDATED_SETTINGS = false
    }

    private var numberOfPageCurrent: Int by DelegatesExt.preference(
        this, NUMBER_OF_PAGE, DEFAULT_NUMBER_OF_PAGE)

    private var numberPerPageCurrent: Int by DelegatesExt.preference(
        this, NUMBER_PER_PAGE, DEFAULT_NUMBER_PER_PAGE)

    private lateinit var prefs: SharedPreferences

    private var orderCurrent: String? = null

    private var updatedSettingsCurrent: Boolean by DelegatesExt.preference(
        this, UPDATED_SETTINGS, DEFAULT_UPDATED_SETTINGS)

    private val orderValues = arrayOf("Latest", "Oldest", "Popular")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initializeViews()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            save()
            true
        }
        else -> false
    }

    private fun initializeViews() {
        numberOfPageEditText.setText(numberOfPageCurrent.toString())
        numberPerPageEditText.setText(numberPerPageCurrent.toString())
        prefs = getSharedPreferences("default", Context.MODE_PRIVATE)
        orderCurrent = prefs.getString(ORDER, DEFAULT_ORDER)
        orderSpinner.adapter = ArrayAdapter(this@SettingsActivity, android.R.layout.simple_list_item_1, orderValues)
        orderSpinner.setSelection(showSelectionDefaultOrder(orderCurrent))
    }

    private fun showSelectionDefaultOrder(orderCurrent: String?) =
        when(orderCurrent) {
            "latest" -> 0
            "oldest" -> 1
            "popular" -> 2
            else -> 0
        }

    private fun setValueOrder() =
        when(orderSpinner.selectedItem) {
            "Latest" -> "latest"
            "Oldest" -> "oldest"
            "Popular" -> "popular"
            else -> "latest"
        }

    private fun save() {
        with(AlertDialog.Builder(this)) {
            setTitle("Save Changes")
            setMessage("Do you want to save changes?")
            setPositiveButton("Yes") { _, _ ->
                numberOfPageCurrent = convertNumberOfPage(numberOfPageEditText.text.toString())
                numberPerPageCurrent = convertNumberPerPage(numberPerPageEditText.text.toString())
                prefs.edit().putString(ORDER, setValueOrder()).apply()
                updatedSettingsCurrent = true

                toast("Settings saved")
                onBackPressed()
            }
            setNegativeButton("No") { _, _ -> onBackPressed() }
            setNeutralButton("Cancel") { _, _ -> setCancelable(true)}
            show()
        }
    }

    private fun convertNumberOfPage(numberOfPageAux: String) =
        if (numberOfPageAux != "") numberOfPageAux.toInt() else DEFAULT_NUMBER_OF_PAGE

    private fun convertNumberPerPage(numberPerPageAux: String) =
        if (numberPerPageAux != "") numberPerPageAux.toInt() else DEFAULT_NUMBER_PER_PAGE
}