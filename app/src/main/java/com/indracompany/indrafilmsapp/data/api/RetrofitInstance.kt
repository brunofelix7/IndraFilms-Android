package com.indracompany.indrafilmsapp.data.api

import com.indracompany.indrafilmsapp.data.api.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    //  Koin inject
    private val interceptor: AuthInterceptor by inject(AuthInterceptor::class.java)

    //  Api base url
    private const val BASE_URL: String = "https://indrafilmsapi.herokuapp.com/api/"

    val apiService: ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(okHttpClient.build())
            addConverterFactory(GsonConverterFactory.create())
        }
    }

    private val okHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(1, TimeUnit.MINUTES)
        readTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)
        addInterceptor(interceptor)
        build()
    }
}