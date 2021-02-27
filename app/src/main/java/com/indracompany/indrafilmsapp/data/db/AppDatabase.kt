package com.indracompany.indrafilmsapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.indracompany.indrafilmsapp.data.db.DatabaseSchema.dbName
import com.indracompany.indrafilmsapp.data.db.DatabaseSchema.dbVersion
import com.indracompany.indrafilmsapp.data.db.dao.UserDao
import com.indracompany.indrafilmsapp.data.db.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = dbVersion,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    dbName
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}