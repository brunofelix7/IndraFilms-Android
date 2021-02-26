package com.indracompany.indrafilmsapp.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Token
import com.indracompany.indrafilmsapp.data.api.model.User
import com.indracompany.indrafilmsapp.data.api.repository.UserRepository
import com.indracompany.indrafilmsapp.listener.LoginListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    //  LiveData
    private val _data = MutableLiveData<ApiResponse<Token>>()
    val data: LiveData<ApiResponse<Token>> get() = _data

    //  View properties
    var email: String? = null
    var password: String? = null
    var listener: LoginListener? = null

    fun onBtnLoginClick(view: View) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            listener?.onError("Todos os campos são obrigatórios.")
            return
        }

        viewModelScope.launch(Dispatchers.Main) {
            listener?.onLoading()
        }

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.userLogin(User(email!!, password!!))

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _data.value = response.body()
                    listener?.onSuccess(data)
                }
            } else {
                withContext(Dispatchers.Main) {
                    listener?.onError(response.errorBody()?.string()!!)
                }
            }
        }
    }
}