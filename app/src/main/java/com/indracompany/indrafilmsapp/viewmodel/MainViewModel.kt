package com.indracompany.indrafilmsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.data.api.repository.MovieRepository
import com.indracompany.indrafilmsapp.listener.MainListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    //  LiveData
    private val _movies = MutableLiveData<ApiResponse<List<Movie>>>()
    val movies: LiveData<ApiResponse<List<Movie>>> get() = _movies

    //  Listener
    var listener: MainListener? = null

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            listener?.onLoading()
        }

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.fetchMovies()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _movies.value = response.body()
                    listener?.onSuccess(movies)
                }
            } else {
                withContext(Dispatchers.Main) {
                    listener?.onError(response.errorBody()?.string()!!)
                }
            }
        }
    }
}