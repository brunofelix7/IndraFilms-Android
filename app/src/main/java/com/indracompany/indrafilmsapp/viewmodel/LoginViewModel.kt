package com.indracompany.indrafilmsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indracompany.indrafilmsapp.data.api.ApiResult
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Token
import com.indracompany.indrafilmsapp.data.api.model.User
import com.indracompany.indrafilmsapp.data.api.repository.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: IUserRepository
) : ViewModel() {

    private val _userToken = MutableLiveData<UiState>()
    val userToken: LiveData<UiState> get() = _userToken

    fun userLogin(user: User) {
        if (user.email.isNullOrEmpty() || user.password.isNullOrEmpty()) {
            _userToken.value = UiState.Error("All fields are required.")
            return
        }

        _userToken.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            when(val response = repository.userLogin(user)) {
                is ApiResult.Error ->  {
                    withContext(Dispatchers.Main) {
                        _userToken.value = UiState.Error(response.message ?: "Error response.")
                    }
                }
                is ApiResult.Success -> {
                    val result = response.data
                    if (result == null) {
                        withContext(Dispatchers.Main) {
                            _userToken.value = UiState.Error("Unexpected error.")
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            _userToken.value = UiState.Success(result)
                        }
                    }
                }
            }
        }
    }

    sealed class UiState {
        object Loading: UiState()
        class Error(val message: String): UiState()
        class Success(val result: ApiResponse<Token>): UiState()
    }
}