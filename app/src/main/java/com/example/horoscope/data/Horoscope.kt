package com.example.horoscope.data

import com.example.horoscope.R

sealed class Horoscope (val id:String, val image:Int, val name:Int) {
    object Aries : Horoscope("aries", R.drawable.aries_icon, R.string.horoscope_name_aries)
    object Taurus : Horoscope("taurus", R.drawable.taurus_icon, R.string.horoscope_name_taurus)
    object Gemini : Horoscope("gemini", R.drawable.gemini_icon, R.string.horoscope_name_gemini)
    object Cancer : Horoscope("cancer", R.drawable.cancer_icon, R.string.horoscope_name_cancer)
    object Leo : Horoscope("leo", R.drawable.leo_icon, R.string.horoscope_name_leo)
    object Virgo : Horoscope("virgo", R.drawable.virgo_icon, R.string.horoscope_name_virgo)
    object Libra : Horoscope("libra", R.drawable.libra_icon, R.string.horoscope_name_libra)
    object Scorpio : Horoscope("scorpio", R.drawable.scorpio_icon, R.string.horoscope_name_scorpio)
    object Sagittarius : Horoscope("sagittarius", R.drawable.sagittarius_icon, R.string.horoscope_name_sagittarius)
    object Capricorn : Horoscope("capricorn", R.drawable.capricorn_icon, R.string.horoscope_name_capricorn)
    object Aquarius : Horoscope("aquarius", R.drawable.aquarius_icon, R.string.horoscope_name_aquarius)
    object Pisces : Horoscope("pisces", R.drawable.pisces_icon, R.string.horoscope_name_pisces)
}