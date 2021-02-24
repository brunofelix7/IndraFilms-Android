package com.indracompany.indrafilmsapp.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.ui.login.LoginActivity

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.logout() {
    val pref = this.getSharedPreferences(this.getString(R.string.app_pref), Context.MODE_PRIVATE)
    with (pref.edit()) {
        clear()
        apply()
    }
    startActivity(Intent(this, LoginActivity::class.java))
    finish()
}