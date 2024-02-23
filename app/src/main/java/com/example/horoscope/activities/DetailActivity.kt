package com.example.horoscope.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscope.R
import com.example.horoscope.data.Horoscope
import com.example.horoscope.data.HoroscopeProvider
import com.example.horoscope.utils.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "HOROSCOPE_ID"
    }

    private lateinit var session:SessionManager

    private var currentHoroscopeIndex:Int = -1
    private var horoscopeId:String? = null
    private lateinit var horoscope:Horoscope
    private var isFavorite:Boolean = false

    private lateinit var horoscopeTextView:TextView
    private lateinit var horoscopeImageView:ImageView
    private lateinit var horoscopeFavoriteImageButton:ImageButton
    private lateinit var horoscopeLuckTextView:TextView
    private lateinit var progress:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        session = SessionManager(this)

        horoscopeId = intent.getStringExtra(EXTRA_ID)
        horoscope = HoroscopeProvider().getHoroscope(horoscopeId!!)
        currentHoroscopeIndex = HoroscopeProvider().getHoroscopeIndex(horoscope)

        initView()

        loadData()
    }

    private fun initView() {
        // Show back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        //supportActionBar?.setDisplayShowHomeEnabled(true);

        horoscopeTextView = findViewById(R.id.horoscopeTextView)
        horoscopeImageView = findViewById(R.id.horoscopeImageView)
        horoscopeFavoriteImageButton = findViewById(R.id.horoscopeFavoriteImageButton)
        horoscopeLuckTextView = findViewById(R.id.horoscopeLuckTextView)
        progress = findViewById(R.id.progress)

        horoscopeFavoriteImageButton.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                session.setFavoriteHoroscope(horoscope.id)
            } else {
                session.setFavoriteHoroscope("")
            }
            setFavoriteDrawable()
        }
    }

    private fun loadData() {
        horoscope = HoroscopeProvider().getHoroscope(currentHoroscopeIndex)
        isFavorite = horoscope.id == session.getFavoriteHoroscope()

        // Set title
        supportActionBar?.setTitle(horoscope.name);
        supportActionBar?.setSubtitle(horoscope.dates);

        horoscopeTextView.text = getString(horoscope.name)
        horoscopeImageView.setImageResource(horoscope.image)

        setFavoriteDrawable()

        getHoroscopeLuck()
    }

    private fun setFavoriteDrawable () {
        val favDrawableId = if (isFavorite) {
            R.drawable.ic_favorite_selected
        } else {
            R.drawable.ic_favorite
        }
        horoscopeFavoriteImageButton.setImageResource(favDrawableId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.horoscope_menu, menu)
        return true
    }

    // this event will enable the back
    // function to the button on press
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menu_prev -> {
                if (currentHoroscopeIndex == 0) {
                    currentHoroscopeIndex = HoroscopeProvider().getHoroscopes().size
                }
                currentHoroscopeIndex--
                loadData()
                return true
            }
            R.id.menu_next -> {
                currentHoroscopeIndex ++
                if (currentHoroscopeIndex == HoroscopeProvider().getHoroscopes().size) {
                    currentHoroscopeIndex = 0
                }
                loadData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getHoroscopeLuck() {
        progress.visibility = View.VISIBLE
        horoscopeLuckTextView.text = ""
        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val result = HoroscopeProvider().getHoroscopeLuck(horoscope.id)
            runOnUiThread {
                // Modificar UI
                progress.visibility = View.GONE
                if (result != null) {
                    horoscopeLuckTextView.text = result
                } else {
                    showErrorDialog()
                }
            }
        }
    }

    private fun showErrorDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle(R.string.alert_error_title)
            .setMessage(R.string.alert_error_connection)
            .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog?.cancel() }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}