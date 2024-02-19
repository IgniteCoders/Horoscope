package com.example.horoscope.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.horoscope.R
import com.example.horoscope.data.HoroscopeProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    var horoscopeName:String? = null

    lateinit var horoscopeTextView:TextView
    lateinit var horoscopeLuckTextView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        horoscopeTextView = findViewById(R.id.horoscopeTextView)
        horoscopeLuckTextView = findViewById(R.id.horoscopeLuckTextView)

        horoscopeName = intent.getStringExtra("HOROSCOPE_NAME")

        horoscopeTextView.text = horoscopeName

        getHoroscopeLuck()
    }

    private fun getHoroscopeLuck() {
        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val result = HoroscopeProvider().getHoroscopeLuck(horoscopeName!!)
            runOnUiThread {
                // Modificar UI
                horoscopeLuckTextView.text = result
            }
        }
    }
}