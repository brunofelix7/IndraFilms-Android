package com.indracompany.indrafilmsapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Token
import com.indracompany.indrafilmsapp.databinding.ActivityLoginBinding
import com.indracompany.indrafilmsapp.extension.hideKeyboard
import com.indracompany.indrafilmsapp.extension.toast
import com.indracompany.indrafilmsapp.listener.LoginListener
import com.indracompany.indrafilmsapp.util.saveToken
import com.indracompany.indrafilmsapp.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(), LoginListener {

    //  ViewBinding
    private lateinit var binding: ActivityLoginBinding

    //  Koin inject
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
    }

    override fun onLoading() {
        hideKeyboard()
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess(liveData: LiveData<ApiResponse<Token>>) {
        liveData.observe(this, { data ->
            saveToken(this, data?.body?.token!!)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }

    override fun onError(message: String) {
        binding.progressBar.visibility = View.GONE
        toast(message)
    }

    fun initializeViews() {
        binding = ActivityLoginBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        binding.viewModel = viewModel

        viewModel.listener = this
    }

}