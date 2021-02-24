package com.indracompany.indrafilmsapp.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.databinding.ActivityDetailsBinding
import com.indracompany.indrafilmsapp.ui.BaseActivity
import com.indracompany.indrafilmsapp.util.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity(), BaseActivity {

    //  ViewBinding
    private lateinit var binding: ActivityDetailsBinding

    //  Koin inject
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
    }

    override fun initializeViews() {
        binding = ActivityDetailsBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        binding.viewModel = viewModel

        val movie = intent.extras?.getParcelable<Movie>(resources.getString(R.string.key_movie_details))

        toolbarSettings()
        fillFields(movie)
    }

    private fun toolbarSettings() {
        binding.includeToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.includeToolbar.toolbar.setOnClickListener {
            finish()
        }
    }

    private fun fillFields(movie: Movie?) {
        if (movie != null) {
            loadImage(binding.imgPoster, movie.posterPath ?: "")
            //viewModel.imgPoster = movie.posterPath
            viewModel.title = movie.title
            viewModel.releaseDate = movie.releaseDate
            viewModel.average = movie.voteAverage.toString()
            viewModel.overview = movie.overview

            changeColorByAverage(movie.voteAverage)
        }
    }

    private fun changeColorByAverage(average: Double?) {
        if (average!! < 7.0) {
            binding.txtAverage.setTextColor(ContextCompat.getColor(this, R.color.red))
        } else {
            binding.txtAverage.setTextColor(ContextCompat.getColor(this, R.color.green))
        }
    }
}