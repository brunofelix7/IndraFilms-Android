package com.indracompany.indrafilmsapp.data.api

import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.data.api.model.TokenResponse
import com.indracompany.indrafilmsapp.data.api.model.User
import com.indracompany.indrafilmsapp.util.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface MyApi {

    companion object {
        operator fun invoke(): MyApi {
            val okHttpClient = OkHttpClient.Builder().apply {
                connectTimeout(1, TimeUnit.MINUTES)
                readTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
                build()
            }

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

    @POST("auth")
    fun login(@Body user: User): Call<TokenResponse>

    @GET("movies")
    fun movies(@Header("Authorization") token: String): Call<List<Movie>>

}