package com.indracompany.indrafilmsapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.adapter.MovieAdapter
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.session.SessionManager
import com.indracompany.indrafilmsapp.databinding.ActivityMainBinding
import com.indracompany.indrafilmsapp.extension.toast
import com.indracompany.indrafilmsapp.listener.MainListener
import com.indracompany.indrafilmsapp.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainListener {

    //  ViewBinding
    private lateinit var binding: ActivityMainBinding

    //  Koin inject
    private val viewModel: MainViewModel by viewModel()
    private val sessionManager: SessionManager by inject()

    companion object {
        const val KEY_MOVIE: String = "key_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
    }

    private fun initializeViews() {
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        viewModel.listener = this
        viewModel.fetchMovies()

        toolbarSettings()
    }

    override fun onLoading() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            rvMovies.visibility = View.GONE
        }
    }

    override fun onSuccess(liveData: LiveData<ApiResponse<List<Movie>>>) {
        liveData.observe(this, { data ->
            binding.progressBar.visibility = View.GONE
            binding.rvMovies.visibility = View.VISIBLE
            binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
            binding.rvMovies.setHasFixedSize(true)
            binding.rvMovies.adapter = MovieAdapter(data?.body!!, this)
        })
    }

    override fun onError(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.rvMovies.visibility = View.GONE
        toast(message)
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
}