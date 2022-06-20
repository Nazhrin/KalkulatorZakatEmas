package org.d3if0098.kalkulatorzakatemas.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/Nazhrin/dataKalkulatorZakat/main/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ZakatApiService {
        @GET("dataKalkulator.json")
        suspend fun getZakat(): String
    }
object ZakatApi{
    val service: ZakatApiService by lazy{
        retrofit.create(ZakatApiService::class.java)
    }
}
