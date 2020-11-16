package com.indracompany.indrafilmsapp.util

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.google.gson.Gson
import java.util.*

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
