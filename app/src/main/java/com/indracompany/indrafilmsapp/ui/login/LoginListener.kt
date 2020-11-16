package com.indracompany.indrafilmsapp.ui.login

import androidx.lifecycle.LiveData
import com.indracompany.indrafilmsapp.data.api.response.TokenResponse

interface LoginListener {

    fun onStarted()
    fun onSuccess(response: LiveData<TokenResponse>)
    fun onError(message: String)

}