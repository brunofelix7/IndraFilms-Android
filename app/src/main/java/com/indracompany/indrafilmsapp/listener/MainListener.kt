package com.indracompany.indrafilmsapp.listener

import android.view.View
import androidx.lifecycle.LiveData
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Movie

interface MainListener {
    fun onLoading()
    fun onSuccess(liveData: LiveData<ApiResponse<List<Movie>>>)
    fun onError(message: String)
    fun onItemClick(view: View, movie: Movie)
}