package com.wtf.sample.db

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Query("select * from user inner join auth_token on user.login=auth_token.login and auth_token.isLogin=1  and auth_token.token=:token")
    fun getLoginUser(token: String): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)
}