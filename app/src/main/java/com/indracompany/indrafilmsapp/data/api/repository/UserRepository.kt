package com.indracompany.indrafilmsapp.data.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.indracompany.indrafilmsapp.data.api.IndraFilmsApi
import com.indracompany.indrafilmsapp.data.api.response.TokenResponse
import com.indracompany.indrafilmsapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun userLogin(user: User) : LiveData<TokenResponse> {
        val loginResponse = MutableLiveData<TokenResponse>()

        IndraFilmsApi().login(user).enqueue(object: Callback<TokenResponse>{
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if (response.isSuccessful) {
                    loginResponse.value = response.body()
                } else {
                    loginResponse.value = Gson().fromJson(response.errorBody()?.string(), TokenResponse::class.java)
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                loginResponse.value?.message = t.message!!
            }
        })
        return loginResponse
    }

}