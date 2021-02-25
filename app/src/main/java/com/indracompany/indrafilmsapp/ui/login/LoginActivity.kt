package com.indracompany.indrafilmsapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Token
import com.indracompany.indrafilmsapp.databinding.ActivityLoginBinding
import com.indracompany.indrafilmsapp.extension.toast
import com.indracompany.indrafilmsapp.ui.BaseActivity
import com.indracompany.indrafilmsapp.ui.main.MainActivity
import com.indracompany.indrafilmsapp.util.saveToken
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(), BaseActivity, LoginListener {

    //  ViewBinding
    private lateinit var binding: ActivityLoginBinding

    //  Koin inject
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
    }

    override fun onStarted() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess(response: LiveData<ApiResponse<Token>>) {
        response.observe(this, { data ->
            if (data.statusCode == 200) {
                saveToken(this, data.body?.token!!)

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                binding.progressBar.visibility = View.GONE
                toast(data.message)
            }
        })
    }

    override fun onError(message: String) {
        binding.progressBar.visibility = View.GONE
        toast(message)
    }

    override fun initializeViews() {
        binding = ActivityLoginBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        binding.viewModel = viewModel

        viewModel.listener = this
    }

}