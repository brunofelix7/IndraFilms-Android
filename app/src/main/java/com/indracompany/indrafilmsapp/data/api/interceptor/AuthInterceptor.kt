package com.indracompany.indrafilmsapp.data.api.interceptor

import com.indracompany.indrafilmsapp.session.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sessionManager: SessionManager
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.let { token ->
            requestBuilder.addHeader("Authorization", token)
        }

        return chain.proceed(requestBuilder.build())
    }
}