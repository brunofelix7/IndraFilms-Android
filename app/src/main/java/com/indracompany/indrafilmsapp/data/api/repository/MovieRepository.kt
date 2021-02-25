package com.indracompany.indrafilmsapp.data.api.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.indracompany.indrafilmsapp.data.api.MyApi
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.util.APP_TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val api: MyApi) {

    fun listMovies(token: String) : LiveData<ApiResponse<List<Movie>>> {
        val liveData = MutableLiveData<ApiResponse<List<Movie>>>()

        api.movies(token).enqueue(object: Callback<ApiResponse<List<Movie>>> {
            override fun onResponse(call: Call<ApiResponse<List<Movie>>>, response: Response<ApiResponse<List<Movie>>>) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<Movie>>>, t: Throwable) {
                Log.d(APP_TAG, t.message!!)
            }
        })
        return liveData
    }

}