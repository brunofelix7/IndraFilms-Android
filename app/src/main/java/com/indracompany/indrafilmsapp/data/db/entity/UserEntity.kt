package com.indracompany.indrafilmsapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.indracompany.indrafilmsapp.data.db.DatabaseSchema.currentUserId
import com.indracompany.indrafilmsapp.data.db.DatabaseSchema.tbUsers

@Entity(tableName = tbUsers)
data class UserEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int = currentUserId,

    val token: String

)