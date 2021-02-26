package com.indracompany.indrafilmsapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.adapter.MovieAdapter
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Movie
import com.indracompany.indrafilmsapp.databinding.ActivityMainBinding
import com.indracompany.indrafilmsapp.extension.toast
import com.indracompany.indrafilmsapp.listener.MainListener
import com.indracompany.indrafilmsapp.viewmodel.MainViewModel
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
        viewModel.fetchMovies()

        toolbarSettings()
    }

    private fun toolbarSettings() {
        binding.includeToolbar.toolbar.inflateMenu(R.menu.main_menu)
        binding.includeToolbar.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_logout -> {
                    logout()
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

    private fun logout() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            val pref = this.getSharedPreferences(this.getString(R.string.app_pref), Context.MODE_PRIVATE)
            with(pref.edit()) {
                clear()
                apply()
            }
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No") {_, _ ->

        }
        builder.setTitle("Logout the application?")
        builder.setMessage("Are you sure you want to logout the application?")
        builder.create()
        builder.show()
    }
}