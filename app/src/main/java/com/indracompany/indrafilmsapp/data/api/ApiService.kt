package com.indracompany.indrafilmsapp.data.api

import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.data.api.model.Token
import com.indracompany.indrafilmsapp.data.api.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth")
    suspend fun login(@Body user: User): Response<ApiResponse<Token>>

    @GET("movies")
    suspend fun fetchMovies(): Response<ApiResponse<List<Movie>>>

}