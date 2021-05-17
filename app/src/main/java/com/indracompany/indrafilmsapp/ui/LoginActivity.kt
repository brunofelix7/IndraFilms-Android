package com.indracompany.indrafilmsapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.indracompany.indrafilmsapp.data.api.model.User
import com.indracompany.indrafilmsapp.databinding.ActivityLoginBinding
import com.indracompany.indrafilmsapp.extension.hideKeyboard
import com.indracompany.indrafilmsapp.extension.toast
import com.indracompany.indrafilmsapp.session.SessionManager
import com.indracompany.indrafilmsapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
        observeData()
    }

    private fun initializeViews() {
        binding = ActivityLoginBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()

            viewModel.userLogin(User(email, password))
        }
    }

    private fun observeData() {
        viewModel.userToken.observe(this@LoginActivity, { uiState ->
            when (uiState) {
                is LoginViewModel.UiState.Loading -> {
                    hideKeyboard()
                    binding.progressBar.visibility = View.VISIBLE
                }
                is LoginViewModel.UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    toast(uiState.message)
                }
                is LoginViewModel.UiState.Success -> {
                    sessionManager.saveAuthToken(uiState.result.body?.token!!)
                    startActivity(Intent(this@LoginActivity, MovieActivity::class.java))
                    finish()
                }
            }
        })
    }
}