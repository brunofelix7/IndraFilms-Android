package com.indracompany.indrafilmsapp.di

import android.content.Context
import com.indracompany.indrafilmsapp.data.api.ApiService
import com.indracompany.indrafilmsapp.data.api.interceptor.AuthInterceptor
import com.indracompany.indrafilmsapp.data.api.repository.IMovieRepository
import com.indracompany.indrafilmsapp.data.api.repository.IUserRepository
import com.indracompany.indrafilmsapp.data.api.repository.MovieRepository
import com.indracompany.indrafilmsapp.data.api.repository.UserRepository
import com.indracompany.indrafilmsapp.other.Constants.API_URL
import com.indracompany.indrafilmsapp.session.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context) = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(provideInterceptor(context))
        .build()

    @Singleton
    @Provides
    fun provideApi(okHttpClient: OkHttpClient): ApiService = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideAuthRepository(api: ApiService): IUserRepository = UserRepository(api)

    @Singleton
    @Provides
    fun provideMovieRepository(api: ApiService): IMovieRepository = MovieRepository(api)

    @Singleton
    @Provides
    fun provideSessionManager(@ApplicationContext context: Context) = SessionManager(context)

    @Singleton
    @Provides
    fun provideInterceptor(@ApplicationContext context: Context): AuthInterceptor =
        AuthInterceptor(provideSessionManager(context))
}
