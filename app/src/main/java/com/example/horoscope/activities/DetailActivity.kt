package com.example.horoscope.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscope.R
import com.example.horoscope.data.Horoscope
import com.example.horoscope.data.HoroscopeProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {

    private var currentHoroscopeIndex:Int = -1
    private var horoscopeId:String? = null
    private lateinit var horoscope:Horoscope

    private lateinit var horoscopeTextView:TextView
    private lateinit var horoscopeImageView:ImageView
    private lateinit var horoscopeLuckTextView:TextView
    private lateinit var progress:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        horoscopeId = intent.getStringExtra("HOROSCOPE_ID")
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
        horoscopeLuckTextView = findViewById(R.id.horoscopeLuckTextView)
        progress = findViewById(R.id.progress)
    }

    private fun loadData() {
        horoscope = HoroscopeProvider().getHoroscope(currentHoroscopeIndex)

        // Set title
        supportActionBar?.setTitle(horoscope.name);
        supportActionBar?.setSubtitle(horoscope.dates);

        horoscopeTextView.text = getString(horoscope.name)
        horoscopeImageView.setImageResource(horoscope.image)

        getHoroscopeLuck()
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
                horoscopeLuckTextView.text = result
                progress.visibility = View.GONE
            }
        }
    }
}