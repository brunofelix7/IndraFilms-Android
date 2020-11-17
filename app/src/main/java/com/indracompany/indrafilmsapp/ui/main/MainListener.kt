package com.indracompany.indrafilmsapp.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import com.indracompany.indrafilmsapp.data.api.response.MovieResponse

interface MainListener {

    fun onStarted()
    fun onCompleted(response: LiveData<List<MovieResponse>>)
    fun onItemClick(view: View, movie: MovieResponse)

}