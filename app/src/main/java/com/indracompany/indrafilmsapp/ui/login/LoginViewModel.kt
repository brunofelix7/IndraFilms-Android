package com.indracompany.indrafilmsapp.ui.login

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.indracompany.indrafilmsapp.R
import com.indracompany.indrafilmsapp.data.api.repository.UserRepository
import com.indracompany.indrafilmsapp.data.api.model.User

class LoginViewModel(
    application: Application,
    private val repository: UserRepository
) : AndroidViewModel(application) {

    var email: String? = null
    var password: String? = null
    var listener: LoginListener? = null

    fun onBtnLoginClick(view: View) {
        listener?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            listener?.onError(getApplication<Application>().resources.getString(R.string.fields_required))
            return
        }

        listener?.onSuccess(repository.userLogin(User(email!!, password!!)))
    }

}