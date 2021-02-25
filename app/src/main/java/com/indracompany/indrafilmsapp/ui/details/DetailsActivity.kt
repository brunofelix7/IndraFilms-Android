package com.indracompany.indrafilmsapp.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.databinding.ActivityDetailsBinding
import com.indracompany.indrafilmsapp.ui.BaseActivity
import com.indracompany.indrafilmsapp.util.loadImage

class DetailsActivity : AppCompatActivity(), BaseActivity {

    //  ViewBinding
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
    }

    override fun initializeViews() {
        binding = ActivityDetailsBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

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
            binding.txtTitle.text = movie.title
            binding.txtReleaseDate.text = movie.releaseDate
            binding.txtAverage.text = movie.voteAverage.toString()
            binding.txtOverview.text = movie.overview

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