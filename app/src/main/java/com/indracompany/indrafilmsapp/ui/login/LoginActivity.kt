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

    var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding?.viewModel = viewModel

        viewModel.loginListener = this
    }

    override fun onStarted() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    override fun onSuccess(response: LiveData<TokenResponse>) {
        response.observe(this, { tokenResponse ->
            if (tokenResponse?.token != null) {
                saveToken(this, tokenResponse.token)

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                binding?.progressBar?.visibility = View.GONE
                toast(tokenResponse.message)
            }
        })
    }

    override fun onError(message: String) {
        binding?.progressBar?.visibility = View.GONE
        toast(message)
    }

}