package com.wtf.sample.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wtf.sample.db.entity.UserEntity

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 下午5:59
 */
@Dao
interface UserDao {
    @Query("select * from user inner join auth_token on user.login=auth_token.login and auth_token.isLogin=1")
    fun getLoginUser(): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity)
}