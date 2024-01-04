package com.dailyapps.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.dailyapps.data.local.room.entity.StudentEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class StudentDao: BaseDao<StudentEntity>() {

    open suspend fun replaceAllStudent(entites: List<StudentEntity>) {
        deleteAllStudent()
        insertAll(entites)
    }

    @Query("SELECT * FROM student ORDER BY id ASC")
    abstract suspend fun selectAllStudent(): StudentEntity

    @Query("DELETE FROM student")
    abstract suspend fun deleteAllStudent(): Int

    @Query("SELECT * FROM student ORDER BY id ASC")
    abstract fun selectALlStudentAsFlow(): Flow<List<StudentEntity>>

}