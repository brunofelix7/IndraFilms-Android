package com.indracompany.indrafilmsapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.data.api.response.MovieResponse
import com.indracompany.indrafilmsapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private var binding: ActivityDetailsBinding? = null
    private var viewModel: DetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        binding?.viewModel = viewModel

        val movie = intent.extras?.getParcelable<MovieResponse>(resources.getString(R.string.key_movie_details))

        toolbarConfig()
        bindingViews(movie)
    }

    fun bindingViews(movie: MovieResponse?) {
        if (movie != null) {
            viewModel?.imgPoster = movie.posterPath
            viewModel?.title = movie.title
            viewModel?.releaseDate = movie.releaseDate
            viewModel?.average = movie.voteAverage.toString()
            viewModel?.overview = movie.overview

            changeColorByAverage(movie.voteAverage)
        }
    }

    fun toolbarConfig() {
        binding?.includeToolbar?.toolbar?.setNavigationIcon(R.drawable.ic_arrow_left)
        binding?.includeToolbar?.toolbar?.setOnClickListener {
            finish()
        }
    }

    fun changeColorByAverage(average: Double?) {
        if (average!! < 7.0) {
            binding?.txtAverage?.setTextColor(ContextCompat.getColor(this, R.color.red))
        } else {
            binding?.txtAverage?.setTextColor(ContextCompat.getColor(this, R.color.green))
        }
    }
}