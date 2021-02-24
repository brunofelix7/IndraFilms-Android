package com.indracompany.indrafilmsapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.adapter.MovieAdapter
import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.databinding.ActivityMainBinding
import com.indracompany.indrafilmsapp.extension.logout
import com.indracompany.indrafilmsapp.extension.toast
import com.indracompany.indrafilmsapp.ui.details.DetailsActivity
import com.indracompany.indrafilmsapp.util.getToken
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainListener {

    //  ViewBinding
    private lateinit var binding: ActivityMainBinding

    //  Koin inject
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
    }

    override fun onStarted() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onCompleted(response: LiveData<List<Movie>>) {
        response.observe(this, { data ->
            binding.progressBar.visibility = View.GONE
            if (data != null) {
                binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
                binding.rvMovies.setHasFixedSize(true)
                binding.rvMovies.adapter = MovieAdapter(data, this)
            } else {
                toast(resources.getString(R.string.msg_error))
            }
        })
    }

    override fun onItemClick(view: View, movie: Movie) {
        when (view.id) {
            R.id.cardMovie -> {
                startActivity(
                    Intent(this, DetailsActivity::class.java).apply {
                        putExtra(resources.getString(R.string.key_movie_details), movie)
                    }
                )
            }
        }
    }

    private fun initializeViews() {
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        viewModel.listener = this
        viewModel.listMovies(getToken(this)!!)

        toolbarSettings()
    }

    private fun toolbarSettings() {
        binding.includeToolbar.toolbarMain.inflateMenu(R.menu.main_menu)
        binding.includeToolbar.toolbarMain.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_logout -> {
                    logout()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
    }

}