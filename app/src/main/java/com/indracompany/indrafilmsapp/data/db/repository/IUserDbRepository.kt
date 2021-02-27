package com.indracompany.indrafilmsapp.data.db.repository

import androidx.lifecycle.LiveData
import com.indracompany.indrafilmsapp.data.db.entity.UserEntity

interface IUserDbRepository {

    suspend fun insert(userEntity: UserEntity): Long

    suspend fun findById(id: Int): UserEntity

}