package org.d3if0098.kalkulatorzakatemas.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0098.kalkulatorzakatemas.data.model.Orang
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/Nazhrin/dataKalkulatorZakat/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ZakatApiService {
        @GET("dataOrang.json")
        suspend fun getZakat(): List<Orang>
}
object ZakatApi{
    val service: ZakatApiService by lazy{
        retrofit.create(ZakatApiService::class.java)
    }
    fun getZakatUrl(nama: String): String{
        return "$BASE_URL$nama.jpg"
    }
}
