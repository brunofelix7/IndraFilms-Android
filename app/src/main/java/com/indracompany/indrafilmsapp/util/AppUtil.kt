package com.indracompany.indrafilmsapp.util

import android.content.Context
import com.indracompany.indrafilmsapp.R

fun saveToken(context: Context, token: String) {
    val pref = context.getSharedPreferences(context.getString(R.string.app_pref), Context.MODE_PRIVATE)
    with (pref.edit()) {
        putString(context.getString(com.indracompany.indrafilmsapp.R.string.key_token), token)
        apply()
    }
}

fun getToken(context: Context) : String? {
    val pref = context.getSharedPreferences(context.getString(R.string.app_pref), Context.MODE_PRIVATE)
    return pref.getString(context.getString(R.string.key_token), null)
}