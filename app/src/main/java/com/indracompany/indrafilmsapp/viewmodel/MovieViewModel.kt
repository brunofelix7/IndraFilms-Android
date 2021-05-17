package com.indracompany.indrafilmsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indracompany.indrafilmsapp.data.api.ApiResult
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.data.api.repository.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: IMovieRepository
) : ViewModel() {

    private val _movies = MutableLiveData<UiState>()
    val movies: LiveData<UiState> get() = _movies

    fun fetchMovies() {
        _movies.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.fetchMovies()) {
                is ApiResult.Error -> {
                    withContext(Dispatchers.Main) {
                        _movies.value = UiState.Error(response.message ?: "")
                    }
                }
                is ApiResult.Success -> {
                    val result = response.data
                    if (result == null) {
                        withContext(Dispatchers.Main) {
                            _movies.value = UiState.Error("Unexpected error.")
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            _movies.value = UiState.Success(result)
                        }
                    }
                }
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        class Error(val message: String) : UiState()
        class Success(val result: ApiResponse<List<Movie>>) : UiState()
    }
}