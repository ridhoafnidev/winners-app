package com.dailyapps.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dailyapps.data.local.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeUser(userEntity: UserEntity)

    @Query("SELECT * FROM user")
    fun getUserByUsernameNisn(): Flow<UserEntity>

    @Query("DELETE FROM user")
    suspend fun deleteAllUser(): Int

}