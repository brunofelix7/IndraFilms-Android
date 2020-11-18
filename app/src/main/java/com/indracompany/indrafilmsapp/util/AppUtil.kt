package com.indracompany.indrafilmsapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.ui.login.LoginActivity

fun saveToken(context: Context, token: String) {
    val pref = context.getSharedPreferences(context.getString(R.string.app_pref), Context.MODE_PRIVATE)
    with (pref.edit()) {
        putString(context.getString(R.string.key_token), "Bearer $token")
        apply()
    }
}

fun getToken(context: Context) : String? {
    val pref = context.getSharedPreferences(context.getString(R.string.app_pref), Context.MODE_PRIVATE)
    return pref.getString(context.getString(R.string.key_token), null)
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