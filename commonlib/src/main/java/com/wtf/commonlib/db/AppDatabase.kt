package com.wtf.commonlib.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.harveyhaha.sample.db.entity.UserEntity
import com.wtf.commonlib.entity.AuthTokenEntity

/**
 * Created by wtf on 1/13/20 8:21 PM.
 */
@Database(entities = [AuthTokenEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun authTokenDao(): AuthTokenDao
}