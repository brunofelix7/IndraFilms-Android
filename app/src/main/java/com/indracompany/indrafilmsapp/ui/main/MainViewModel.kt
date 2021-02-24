package com.indracompany.indrafilmsapp.ui.main

import androidx.lifecycle.ViewModel
import com.indracompany.indrafilmsapp.data.api.repository.MovieRepository

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    var listener: MainListener? = null

    init {
        //  TODO: List here...
    }

    fun listMovies(token: String) {
        listener?.onStarted()
        listener?.onCompleted(repository.listMovies(token))
    }

}