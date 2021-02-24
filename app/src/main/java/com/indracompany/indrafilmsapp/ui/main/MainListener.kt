package com.indracompany.indrafilmsapp.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import com.indracompany.indrafilmsapp.data.api.model.Movie

interface MainListener {

    fun onStarted()
    fun onCompleted(response: LiveData<List<Movie>>)
    fun onItemClick(view: View, movie: Movie)

}