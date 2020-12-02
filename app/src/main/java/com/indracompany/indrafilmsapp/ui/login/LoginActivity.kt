package com.indracompany.indrafilmsapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.data.api.response.TokenResponse
import com.indracompany.indrafilmsapp.databinding.ActivityLoginBinding
import com.indracompany.indrafilmsapp.ui.main.MainActivity
import com.indracompany.indrafilmsapp.util.saveToken
import com.indracompany.indrafilmsapp.util.toast

class LoginActivity : AppCompatActivity(), LoginListener {

    private var binding: ActivityLoginBinding? = null
    private var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingConfig()
    }

    override fun onStarted() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    override fun onSuccess(response: LiveData<TokenResponse>) {
        response.observe(this, { data ->
            if (data?.token != null) {
                saveToken(this, data.token)

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                binding?.progressBar?.visibility = View.GONE
                toast(data.message)
            }
        })
    }

    override fun onError(message: String) {
        binding?.progressBar?.visibility = View.GONE
        toast(message)
    }

    private fun bindingConfig() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding?.viewModel = viewModel
        viewModel?.loginListener = this
    }

}