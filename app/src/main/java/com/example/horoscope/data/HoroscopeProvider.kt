package com.example.horoscope.data

import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import javax.net.ssl.HttpsURLConnection

class HoroscopeProvider {
    suspend fun getHoroscopeLuck(horoscopeId: String): String? {
        val url = URL("https://horoscope-app-api.vercel.app/api/v1/get-horoscope/daily?sign=$horoscopeId&day=TODAY") // URL de la API o endpoint
        var connection: HttpsURLConnection? = null
        var result: String? = null

        try {
            // Crear la conexión HTTP
            connection = url.openConnection() as HttpsURLConnection
            connection.requestMethod = "GET" // Establecer el método GET
            connection.setRequestProperty("Accept", "application/json") // Establecer el tipo de contenido

            // Leer la respuesta de la solicitud
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = readStream (connection.inputStream)
                Log.i("HTTP", "Respuesta: ${response.toString()}")

                val responseObject: JSONObject = JSONObject(response.toString())
                result = responseObject.getJSONObject("data").getString("horoscope_data")
            } else {
                Log.i("HTTP", "Error en la solicitud. Código de respuesta: $responseCode")
            }

        } catch (e: Exception) {
            Log.e("HTTP", "Error en la solicitud. ", e)
        } finally {
            // Cerrar la conexión
            connection?.disconnect()
        }
        return result
    }

    private fun readStream (inputStream: InputStream) : StringBuilder {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val response = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            response.append(line)
        }
        reader.close()
        return response
    }
}