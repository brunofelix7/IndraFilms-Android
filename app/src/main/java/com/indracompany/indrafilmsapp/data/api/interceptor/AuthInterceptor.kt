package com.indracompany.indrafilmsapp.data.api.interceptor

import com.indracompany.indrafilmsapp.session.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.java.KoinJavaComponent.inject

class AuthInterceptor: Interceptor {

    //  Koin inject
    private val sessionManager: SessionManager by inject(SessionManager::class.java)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.let { token ->
            requestBuilder.addHeader("Authorization", token)
        }

        return chain.proceed(requestBuilder.build())
    }
}