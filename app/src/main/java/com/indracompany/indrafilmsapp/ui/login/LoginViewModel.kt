package com.indracompany.indrafilmsapp.ui.login

import android.view.View
import androidx.lifecycle.ViewModel
import com.indracompany.indrafilmsapp.data.api.repository.UserRepository
import com.indracompany.indrafilmsapp.model.User

class LoginViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null
    var loginListener: LoginListener? = null

    fun onBtnLoginClick(view: View) {
        loginListener?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            loginListener?.onError("Campos obrigatórios")
            return
        }

        val response = UserRepository().userLogin(User(email!!, password!!))

        loginListener?.onSuccess(response)
    }

}