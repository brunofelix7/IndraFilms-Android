package com.indracompany.indrafilmsapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.indracompany.indrafilmsapp.session.SessionManager
import com.indracompany.indrafilmsapp.databinding.ActivitySplashBinding
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    //  ViewBinding
    private lateinit var binding: ActivitySplashBinding

    //  Koin inject
    private val sessionManager: SessionManager by inject()

    companion object {
        const val SPLASH_TIME_OUT: Long = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
    }

    private fun initializeViews() {
        binding = ActivitySplashBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            if (sessionManager.fetchAuthToken() != null) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, SPLASH_TIME_OUT)
    }
}