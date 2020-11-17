package com.indracompany.indrafilmsapp.ui.main

import androidx.lifecycle.ViewModel
import com.indracompany.indrafilmsapp.data.api.repository.MovieRepository

class MainViewModel : ViewModel() {

    var mainistener: MainListener? = null

    fun listMovies(token: String) {
        mainistener?.onStarted()

        val response = MovieRepository().listMovies(token)
        mainistener?.onCompleted(response)
    }

}