package com.indracompany.indrafilmsapp.ui.main

import androidx.lifecycle.ViewModel
import com.indracompany.indrafilmsapp.data.api.repository.MovieRepository

class MainViewModel : ViewModel() {

    var mainListener: MainListener? = null

    fun listMovies(token: String) {
        mainListener?.onStarted()

        val response = MovieRepository().listMovies(token)
        mainListener?.onCompleted(response)
    }

}