package com.indracompany.indrafilmsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indracompany.indrafilmsapp.data.db.DatabaseSchema.currentUserId
import com.indracompany.indrafilmsapp.data.db.entity.UserEntity
import com.indracompany.indrafilmsapp.data.db.repository.UserDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(private val repositoryDb: UserDbRepository) : ViewModel() {

    //  LiveData
    private val _user = MutableLiveData<UserEntity>()
    val user: LiveData<UserEntity> get() = _user

    fun fetchUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repositoryDb.findById(currentUserId)

            withContext(Dispatchers.Main) {
                _user.value = result
            }
        }
    }
}