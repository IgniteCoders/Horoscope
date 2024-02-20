package com.example.horoscope.data

import com.example.horoscope.R

sealed class Horoscope (val id:String, val image:Int, val name:Int, val dates:Int) {
    object Aries : Horoscope("aries", R.drawable.aries_icon, R.string.horoscope_name_aries, R.string.horoscope_date_aries)
    object Taurus : Horoscope("taurus", R.drawable.taurus_icon, R.string.horoscope_name_taurus, R.string.horoscope_date_taurus)
    object Gemini : Horoscope("gemini", R.drawable.gemini_icon, R.string.horoscope_name_gemini, R.string.horoscope_date_gemini)
    object Cancer : Horoscope("cancer", R.drawable.cancer_icon, R.string.horoscope_name_cancer, R.string.horoscope_date_cancer)
    object Leo : Horoscope("leo", R.drawable.leo_icon, R.string.horoscope_name_leo, R.string.horoscope_date_leo)
    object Virgo : Horoscope("virgo", R.drawable.virgo_icon, R.string.horoscope_name_virgo, R.string.horoscope_date_virgo)
    object Libra : Horoscope("libra", R.drawable.libra_icon, R.string.horoscope_name_libra, R.string.horoscope_date_libra)
    object Scorpio : Horoscope("scorpio", R.drawable.scorpio_icon, R.string.horoscope_name_scorpio, R.string.horoscope_date_scorpio)
    object Sagittarius : Horoscope("sagittarius", R.drawable.sagittarius_icon, R.string.horoscope_name_sagittarius, R.string.horoscope_date_sagittarius)
    object Capricorn : Horoscope("capricorn", R.drawable.capricorn_icon, R.string.horoscope_name_capricorn, R.string.horoscope_date_capricorn)
    object Aquarius : Horoscope("aquarius", R.drawable.aquarius_icon, R.string.horoscope_name_aquarius, R.string.horoscope_date_aquarius)
    object Pisces : Horoscope("pisces", R.drawable.pisces_icon, R.string.horoscope_name_pisces, R.string.horoscope_date_pisces)
}