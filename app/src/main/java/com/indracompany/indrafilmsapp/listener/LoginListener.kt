package com.indracompany.indrafilmsapp.listener

import androidx.lifecycle.LiveData
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Token

interface LoginListener {
    fun onLoading()
    fun onSuccess(liveData: LiveData<ApiResponse<Token>>)
    fun onError(message: String)
}