package com.dailyapps.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.dailyapps.data.local.room.entity.TeacherEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TeacherDao: BaseDao<TeacherEntity>() {

    open suspend fun replaceAllTeacher(entites: List<TeacherEntity>) {
        deleteAllTeacher()
        insertAll(entites)
    }

    @Query("SELECT * FROM teacher ORDER BY id ASC")
    abstract suspend fun selectAllTeacher(): List<TeacherEntity>

    @Query("DELETE FROM teacher")
    abstract suspend fun deleteAllTeacher(): Int

    @Query("SELECT * FROM teacher ORDER BY id ASC")
    abstract fun selectALlTeacherAsFlow(): Flow<List<TeacherEntity>>

}