package com.indracompany.indrafilmsapp.ui.login

interface LoginListener {

    fun onStarted()
    fun onSuccess()
    fun onError(message: String)

}