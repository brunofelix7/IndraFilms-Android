package com.indracompany.indrafilmsapp.ui.login

import androidx.lifecycle.LiveData
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Token

interface LoginListener {

    fun onStarted()
    fun onSuccess(response: LiveData<ApiResponse<Token>>)
    fun onError(message: String)

}