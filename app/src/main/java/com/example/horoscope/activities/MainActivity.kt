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
        Horoscope(R.drawable.aries_icon, R.string.horoscope_name_aries),
        Horoscope(R.drawable.taurus_icon, R.string.horoscope_name_taurus),
        Horoscope(R.drawable.gemini_icon, R.string.horoscope_name_gemini),
        Horoscope(R.drawable.cancer_icon, R.string.horoscope_name_cancer),
        Horoscope(R.drawable.leo_icon, R.string.horoscope_name_leo),
        Horoscope(R.drawable.virgo_icon, R.string.horoscope_name_virgo),
        Horoscope(R.drawable.libra_icon, R.string.horoscope_name_libra),
        Horoscope(R.drawable.scorpius_icon, R.string.horoscope_name_scorpio),
        Horoscope(R.drawable.sagittarius_icon, R.string.horoscope_name_sagittarius),
        Horoscope(R.drawable.capricornius_icon, R.string.horoscope_name_capricorn),
        Horoscope(R.drawable.aquarius_icon, R.string.horoscope_name_aquarius),
        Horoscope(R.drawable.pisces_icon, R.string.horoscope_name_pisces),
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