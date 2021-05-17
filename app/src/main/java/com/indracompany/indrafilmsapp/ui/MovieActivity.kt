package com.indracompany.indrafilmsapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.adapter.MovieAdapter
import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.session.SessionManager
import com.indracompany.indrafilmsapp.databinding.ActivityMovieBinding
import com.indracompany.indrafilmsapp.extension.toast
import com.indracompany.indrafilmsapp.listener.MovieListener
import com.indracompany.indrafilmsapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieActivity : AppCompatActivity(), MovieListener {

    private lateinit var binding: ActivityMovieBinding

    private val viewModel: MovieViewModel by viewModels()

    @Inject
    lateinit var sessionManager: SessionManager

    companion object {
        const val KEY_MOVIE: String = "key_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
    }

    private fun initializeViews() {
        binding = ActivityMovieBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        toolbarSettings()

        viewModel.fetchMovies()
    }

    override fun onStart() {
        super.onStart()
        observeData()
    }

    private fun observeData() {
        viewModel.movies.observe(this@MovieActivity, { uiState ->
            when (uiState) {
                is MovieViewModel.UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvMovies.visibility = View.GONE
                }
                is MovieViewModel.UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvMovies.visibility = View.GONE
                    toast(uiState.message)
                }
                is MovieViewModel.UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvMovies.visibility = View.VISIBLE
                    binding.rvMovies.layoutManager = GridLayoutManager(this@MovieActivity, 2)
                    binding.rvMovies.setHasFixedSize(true)
                    binding.rvMovies.adapter =
                        MovieAdapter(uiState.result.body!!, this@MovieActivity)
                }
            }
        })
    }

    private fun toolbarSettings() {
        binding.includeToolbar.toolbar.inflateMenu(R.menu.main_menu)
        binding.includeToolbar.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_logout -> {
                    sessionManager.appLogout(this)
                    true
                }
                R.id.menu_sync -> {
                    viewModel.fetchMovies()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onItemClick(view: View, movie: Movie) {
        when (view.id) {
            R.id.cardMovie -> {
                startActivity(
                    Intent(this, DetailActivity::class.java).apply {
                        putExtra(KEY_MOVIE, movie)
                    }
                )
            }
        }
    }
}