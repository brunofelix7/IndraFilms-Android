package com.indracompany.indrafilmsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.databinding.ItemMovieCardBinding
import com.indracompany.indrafilmsapp.listener.MovieListener

class MovieAdapter(
    private val movies: List<Movie>,
    private val listener: MovieListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_card,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.movie = movies[position]
        holder.binding.cardMovie.setOnClickListener {
            listener.onItemClick(holder.binding.cardMovie, movies[position])
        }
    }

    override fun getItemCount() = movies.size

    inner class MovieViewHolder(val binding: ItemMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) { }

}