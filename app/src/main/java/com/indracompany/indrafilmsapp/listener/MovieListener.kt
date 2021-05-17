package com.indracompany.indrafilmsapp.listener

import android.view.View
import com.indracompany.indrafilmsapp.data.api.model.Movie

interface MovieListener {
    fun onItemClick(view: View, movie: Movie)
}