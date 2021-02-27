package com.indracompany.indrafilmsapp.data.db.repository

import androidx.lifecycle.LiveData
import com.indracompany.indrafilmsapp.data.db.dao.UserDao
import com.indracompany.indrafilmsapp.data.db.entity.UserEntity

class UserDbRepository(private val dao: UserDao) : IUserDbRepository {

    override suspend fun insert(userEntity: UserEntity): Long = dao.insert(userEntity)

    override suspend fun findById(id: Int): UserEntity = dao.findById(id)

}