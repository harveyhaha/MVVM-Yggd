package com.wtf.sample.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.wtf.sample.model.User

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 下午5:59
 */
@Dao
interface UserDao {
    @Query("select * from user where  ")
    fun login(name: String): LiveData<User>
}