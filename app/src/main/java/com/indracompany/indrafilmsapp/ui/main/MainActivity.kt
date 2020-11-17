package com.indracompany.indrafilmsapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.adapter.MovieAdapter
import com.indracompany.indrafilmsapp.data.api.response.MovieResponse
import com.indracompany.indrafilmsapp.databinding.ActivityMainBinding
import com.indracompany.indrafilmsapp.ui.login.LoginViewModel
import com.indracompany.indrafilmsapp.util.MY_TAG
import com.indracompany.indrafilmsapp.util.getToken
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
    }

    override fun onStarted() {
        Log.i(MY_TAG, "Buscando...")
    }

    override fun onCompleted(response: LiveData<List<MovieResponse>>) {
        response.observe(this, { data ->
            Log.i(MY_TAG, "Finish")
            if (data != null) {
                binding?.rvMovies?.layoutManager = GridLayoutManager(this, 2)
                binding?.rvMovies?.setHasFixedSize(true)
                binding?.rvMovies?.adapter = MovieAdapter(data, this)
            } else {
                Log.i(MY_TAG, "Error")
            }
        })
    }

    override fun onItemClick(view: View, movie: MovieResponse) {
        when (view.id) {
            R.id.cardMovie -> {
                Log.i(MY_TAG, "${movie}")
            }
        }
    }

}