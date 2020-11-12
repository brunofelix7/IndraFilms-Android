package com.indracompany.indrafilmsapp.ui.login

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.indracompany.indrafilmsapp.R

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val context = getApplication<Application>().applicationContext
    var email: String? = null
    var password: String? = null
    var loginListener: LoginListener? = null

    //  Função para callBack de click no botão de login
    fun onBtnLoginClick(view: View) {
        loginListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            //  TODO: Apresentar mansagem de erro
            loginListener?.onError(context.resources.getString(R.string.login_message_error))
            return
        }
        //  TODO: Sucesso
        loginListener?.onSuccess()
    }

}