package com.example.horoscope.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.horoscope.R
import com.example.horoscope.adapters.HorocopeAdapter
import com.example.horoscope.data.Horoscope

class MainActivity : AppCompatActivity() {

    var horoscopeList:List<Horoscope> = listOf(
        Horoscope.Aries,
        Horoscope.Taurus,
        Horoscope.Gemini,
        Horoscope.Cancer,
        Horoscope.Leo,
        Horoscope.Virgo,
        Horoscope.Libra,
        Horoscope.Scorpio,
        Horoscope.Sagittarius,
        Horoscope.Capricorn,
        Horoscope.Aquarius,
        Horoscope.Pisces
    )

    lateinit var adapter: HorocopeAdapter

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        getString(R.string.horoscope_name_aries)
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)

        adapter = HorocopeAdapter(horoscopeList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}