package com.wtf.sample.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wtf.sample.db.entity.AuthTokenEntity

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 下午5:59
 */
@Dao
interface AuthTokenDao {
    @Query("select * from auth_token where isLogin = 1 ")
    fun getLoginToken(): LiveData<AuthTokenEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToken(authTokenEntity: AuthTokenEntity)

    @Query("DELETE FROM auth_token WHERE login=:login")
    fun deleteAuthToken(login: String)
}