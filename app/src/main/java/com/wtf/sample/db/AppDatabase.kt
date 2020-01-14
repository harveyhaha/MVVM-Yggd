package com.wtf.sample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wtf.sample.db.entity.AuthTokenEntity
import com.wtf.sample.db.entity.UserEntity

/**
 * Created by wtf on 1/13/20 8:21 PM.
 */
@Database(entities = [AuthTokenEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun authTokenDao(): AuthTokenDao
}