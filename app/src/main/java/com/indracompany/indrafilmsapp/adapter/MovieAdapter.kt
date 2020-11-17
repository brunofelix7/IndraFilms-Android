package com.indracompany.indrafilmsapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.data.api.response.MovieResponse
import com.indracompany.indrafilmsapp.databinding.ItemMovieCardBinding
import com.indracompany.indrafilmsapp.ui.main.MainListener
import com.indracompany.indrafilmsapp.util.MY_TAG

class MovieAdapter(
    private val movies: List<MovieResponse>,
    private val listener: MainListener) : RecyclerView.Adapter<MovieAdapter.FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FilmViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_card,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.binding.movie = movies[position]
        holder.binding.cardMovie.setOnClickListener {
            listener.onItemClick(holder.binding.cardMovie, movies[position])
        }
    }

    override fun getItemCount() = movies.size

    /**
     * Classe ViewHolder
     */
    inner class FilmViewHolder(val binding: ItemMovieCardBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}