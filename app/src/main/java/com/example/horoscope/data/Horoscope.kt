package com.example.horoscope.data

import com.example.horoscope.R

sealed class Horoscope (val image:Int, val name:Int) {
    object Aries : Horoscope(R.drawable.aries_icon, R.string.horoscope_name_aries)
    object Taurus : Horoscope(R.drawable.taurus_icon, R.string.horoscope_name_taurus)
    object Gemini : Horoscope(R.drawable.gemini_icon, R.string.horoscope_name_gemini)
    object Cancer : Horoscope(R.drawable.cancer_icon, R.string.horoscope_name_cancer)
    object Leo : Horoscope(R.drawable.leo_icon, R.string.horoscope_name_leo)
    object Virgo : Horoscope(R.drawable.virgo_icon, R.string.horoscope_name_virgo)
    object Libra : Horoscope(R.drawable.libra_icon, R.string.horoscope_name_libra)
    object Scorpio : Horoscope(R.drawable.scorpio_icon, R.string.horoscope_name_scorpio)
    object Sagittarius : Horoscope(R.drawable.sagittarius_icon, R.string.horoscope_name_sagittarius)
    object Capricorn : Horoscope(R.drawable.capricorn_icon, R.string.horoscope_name_capricorn)
    object Aquarius : Horoscope(R.drawable.aquarius_icon, R.string.horoscope_name_aquarius)
    object Pisces : Horoscope(R.drawable.pisces_icon, R.string.horoscope_name_pisces)
}