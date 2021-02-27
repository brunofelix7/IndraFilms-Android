package com.indracompany.indrafilmsapp.session

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.indracompany.indrafilmsapp.ui.LoginActivity

class SessionManager(context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        const val PREF_NAME: String = "app_pref"
        const val USER_TOKEN: String = "user_token"
    }

    fun saveAuthToken(token: String) {
        with (pref.edit()) {
            putString(USER_TOKEN, "Bearer $token")
            apply()
        }
    }

    fun fetchAuthToken(): String? {
        return pref.getString(USER_TOKEN, null)
    }

    fun appLogout(activity: Activity) {
        val builder = AlertDialog.Builder(activity)
        builder.setPositiveButton("Yes") { _, _ ->
            with(pref.edit()) {
                clear()
                apply()
            }

            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            activity.startActivity(intent)
            activity.finish()
        }
        builder.setNegativeButton("No") {_, _ ->

        }
        builder.setTitle("Logout the application?")
        builder.setMessage("Are you sure you want to logout the application?")
        builder.create()
        builder.show()
    }
}