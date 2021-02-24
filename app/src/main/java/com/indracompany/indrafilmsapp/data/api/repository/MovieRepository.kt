package com.indracompany.indrafilmsapp.data.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.indracompany.indrafilmsapp.data.api.MyApi
import com.indracompany.indrafilmsapp.data.api.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val api: MyApi) {

    fun listMovies(token: String) : LiveData<List<Movie>> {
        val movieResponse = MutableLiveData<List<Movie>>()

        api.movies(token).enqueue(object: Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    movieResponse.value = response.body()
                } else {
                    movieResponse.value = null
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                movieResponse.value = null
            }
        })
        return movieResponse
    }

}