package com.indracompany.indrafilmsapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.adapter.MovieAdapter
import com.indracompany.indrafilmsapp.data.api.response.MovieResponse
import com.indracompany.indrafilmsapp.databinding.ActivityMainBinding
import com.indracompany.indrafilmsapp.ui.details.DetailsActivity
import com.indracompany.indrafilmsapp.util.getToken
import com.indracompany.indrafilmsapp.util.logout
import com.indracompany.indrafilmsapp.util.toast

class MainActivity : AppCompatActivity(), MainListener {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding?.viewModel = viewModel

        viewModel.mainistener = this
        viewModel.listMovies(getToken(this)!!)

        toolbarConfig()
    }

    override fun onStarted() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    override fun onCompleted(response: LiveData<List<MovieResponse>>) {
        response.observe(this, { data ->
            binding?.progressBar?.visibility = View.GONE
            if (data != null) {
                binding?.rvMovies?.layoutManager = GridLayoutManager(this, 2)
                binding?.rvMovies?.setHasFixedSize(true)
                binding?.rvMovies?.adapter = MovieAdapter(data, this)
            } else {
                toast(resources.getString(R.string.msg_error))
            }
        })
    }

    override fun onItemClick(view: View, movie: MovieResponse) {
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

    fun toolbarConfig() {
        binding?.includeToolbar?.toolbarMain?.inflateMenu(R.menu.main_menu)
        binding?.includeToolbar?.toolbarMain?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_logout -> {
                    logout()
                    true
                }
                else -> super.onOptionsItemSelected(it)
            }
        }
    }

}