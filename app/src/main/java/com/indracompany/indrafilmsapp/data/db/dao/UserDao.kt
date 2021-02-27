package com.indracompany.indrafilmsapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.indracompany.indrafilmsapp.data.db.DatabaseSchema.currentUserId
import com.indracompany.indrafilmsapp.data.db.DatabaseSchema.tbUsers
import com.indracompany.indrafilmsapp.data.db.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity) : Long

    @Query("SELECT * FROM $tbUsers WHERE id=:id")
    suspend fun findById(id: Int) : UserEntity

}