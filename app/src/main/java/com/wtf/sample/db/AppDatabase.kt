package com.wtf.sample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wtf.sample.model.AuthToken
import com.wtf.sample.model.User

/**
 * Created by wtf on 1/13/20 8:21 PM.
 */
@Database(entities = [AuthToken::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun authTokenDao(): AuthTokenDao
}