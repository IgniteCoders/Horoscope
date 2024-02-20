package com.example.horoscope.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscope.R
import com.example.horoscope.data.Horoscope
import com.example.horoscope.data.HoroscopeProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {

    private var horoscopeId:String? = null
    private lateinit var horoscope:Horoscope

    private lateinit var horoscopeTextView:TextView
    private lateinit var horoscopeImageView:ImageView
    private lateinit var horoscopeLuckTextView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        horoscopeId = intent.getStringExtra("HOROSCOPE_ID")
        horoscope = HoroscopeProvider().getHoroscope(horoscopeId!!)

        initView()

        getHoroscopeLuck()
    }

    private fun initView() {
        // Show back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        //supportActionBar?.setDisplayShowHomeEnabled(true);

        // Set title
        supportActionBar?.setTitle(horoscope.name);
        supportActionBar?.setSubtitle(horoscope.dates);

        // Set icon
        //supportActionBar?.setLogo(horoscope.image);

        horoscopeTextView = findViewById(R.id.horoscopeTextView)
        horoscopeImageView = findViewById(R.id.horoscopeImageView)
        horoscopeLuckTextView = findViewById(R.id.horoscopeLuckTextView)

        horoscopeTextView.text = getString(horoscope.name)
        horoscopeImageView.setImageResource(horoscope.image)
    }

    // this event will enable the back
    // function to the button on press
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getHoroscopeLuck() {
        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val result = HoroscopeProvider().getHoroscopeLuck(horoscope.id)
            runOnUiThread {
                // Modificar UI
                horoscopeLuckTextView.text = result
            }
        }
    }
}