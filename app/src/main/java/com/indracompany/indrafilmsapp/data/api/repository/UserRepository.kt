package com.indracompany.indrafilmsapp.data.api.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.indracompany.indrafilmsapp.data.api.MyApi
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Token
import com.indracompany.indrafilmsapp.data.api.model.User
import com.indracompany.indrafilmsapp.util.APP_TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val api: MyApi) {

    fun userLogin(user: User) : LiveData<ApiResponse<Token>> {
        val liveData = MutableLiveData<ApiResponse<Token>>()

        api.login(user).enqueue(object: Callback<ApiResponse<Token>>{
            override fun onResponse(call: Call<ApiResponse<Token>>, response: Response<ApiResponse<Token>>) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ApiResponse<Token>>, t: Throwable) {
                Log.d(APP_TAG, t.message!!)
            }
        })
        return liveData
    }

}