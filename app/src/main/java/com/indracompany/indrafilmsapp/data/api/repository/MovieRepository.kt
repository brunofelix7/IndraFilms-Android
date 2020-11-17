package com.indracompany.indrafilmsapp.data.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.indracompany.indrafilmsapp.data.api.IndraFilmsApi
import com.indracompany.indrafilmsapp.data.api.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    fun listMovies(token: String) : LiveData<List<MovieResponse>> {
        val movieResponse = MutableLiveData<List<MovieResponse>>()

        IndraFilmsApi().movies(token).enqueue(object: Callback<List<MovieResponse>> {
            override fun onResponse(call: Call<List<MovieResponse>>, response: Response<List<MovieResponse>>) {
                if (response.isSuccessful) {
                    movieResponse.value = response.body()
                } else {
                    movieResponse.value = null
                }
            }

            override fun onFailure(call: Call<List<MovieResponse>>, t: Throwable) {
                movieResponse.value = null
            }
        })
        return movieResponse
    }

}