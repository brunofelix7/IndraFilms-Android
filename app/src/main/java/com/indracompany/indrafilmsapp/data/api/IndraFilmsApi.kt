package com.indracompany.indrafilmsapp.data.api

import com.indracompany.indrafilmsapp.data.api.response.MovieResponse
import com.indracompany.indrafilmsapp.data.api.response.TokenResponse
import com.indracompany.indrafilmsapp.model.User
import com.indracompany.indrafilmsapp.util.apiUrl
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface IndraFilmsApi {

    companion object {
        operator fun invoke(): IndraFilmsApi {
            return Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IndraFilmsApi::class.java)
        }
    }

    @POST("auth")
    fun login(@Body user: User) : Call<TokenResponse>

    @GET("movies")
    fun movies(@Header("Authorization") token: String) : Call<List<MovieResponse>>

}