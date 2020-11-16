package com.indracompany.indrafilmsapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.ui.login.LoginActivity
import com.indracompany.indrafilmsapp.ui.main.MainActivity
import com.indracompany.indrafilmsapp.util.SPLASH_TIME_OUT
import com.indracompany.indrafilmsapp.util.getToken

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            if (getToken(this) != null) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, SPLASH_TIME_OUT)
    }

}