package com.example.horoscope.data

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class HoroscopeProvider {
    suspend fun getHoroscopeLuck() {
        val url = URL("https://horoscope-app-api.vercel.app/api/v1/get-horoscope/daily") // URL de la API o endpoint
        val requestBody = "sign=Aquarius&day=TODAY" // Datos a enviar en el cuerpo de la solicitud

        // Crear la conexión HTTP
        val connection = url.openConnection() as HttpsURLConnection
        connection.requestMethod = "POST" // Establecer el método POST
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded") // Establecer el tipo de contenido

        // Habilitar el envío de datos en el cuerpo de la solicitud
        connection.doOutput = true
        val outputStream = connection.outputStream
        outputStream.write(requestBody.toByteArray())
        outputStream.flush()
        outputStream.close()

        // Leer la respuesta de la solicitud
        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = connection.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))
            val response = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            reader.close()
            println("Respuesta: ${response.toString()}")
        } else {
            println("Error en la solicitud. Código de respuesta: $responseCode")
        }

        // Cerrar la conexión
        connection.disconnect()
    }
}