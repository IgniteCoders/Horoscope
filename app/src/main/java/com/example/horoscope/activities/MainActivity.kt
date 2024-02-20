package com.example.horoscope.activities

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscope.R
import com.example.horoscope.adapters.HoroscopeAdapter
import com.example.horoscope.data.Horoscope
import com.example.horoscope.data.HoroscopeProvider

class MainActivity : AppCompatActivity() {

    private var horoscopeList : List<Horoscope> = HoroscopeProvider().getHoroscopes()

    lateinit var horocopeAdapter: HoroscopeAdapter

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)

        horocopeAdapter = HoroscopeAdapter(horoscopeList) {
            onItemClickListener(it)
        }
        //recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = horocopeAdapter
    }

    private fun onItemClickListener(position:Int) {
        val horoscope:Horoscope = horoscopeList[position]

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("HOROSCOPE_ID", horoscope.id)
        startActivity(intent)
        //Toast.makeText(this, getString(horoscope.name), Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.horoscope_menu, menu)

        initSearchView(menu?.findItem(R.id.menu_search))

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initSearchView(searchItem: MenuItem?) {
        if (searchItem != null) {
            var searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    if (query.isNullOrEmpty()) {
                        horoscopeList = HoroscopeProvider().getHoroscopes()
                    } else {
                        horoscopeList = HoroscopeProvider().getHoroscopes()
                            .filter { getString(it.name).contains(query, true) }
                    }
                    horocopeAdapter.updateData(horoscopeList)
                    return true
                }
            })
        }
    }
}