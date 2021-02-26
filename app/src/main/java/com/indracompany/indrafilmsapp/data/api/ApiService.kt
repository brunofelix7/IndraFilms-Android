package com.indracompany.indrafilmsapp.data.api

import android.content.Context
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.data.api.model.Token
import com.indracompany.indrafilmsapp.data.api.model.User
import com.indracompany.indrafilmsapp.util.BASE_URL
import com.indracompany.indrafilmsapp.util.getToken
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApiService {

    companion object {
        operator fun invoke(context: Context): ApiService {
            val interceptor = Interceptor { chain ->
                val original: Request = chain.request()

                val request: Request = original.newBuilder()
                    .header("Authorization", getToken(context) ?: "")
                    .method(original.method(), original.body())
                    .build()

                chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder().apply {
                connectTimeout(1, TimeUnit.MINUTES)
                readTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
                addInterceptor(interceptor)
                build()
            }

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

    @POST("auth")
    suspend fun login(@Body user: User): Response<ApiResponse<Token>>

    @GET("movies")
    suspend fun fetchMovies(): Response<ApiResponse<List<Movie>>>

}