package com.dailyapps.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.dailyapps.data.local.room.entity.ClassRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ClassRoomDao: BaseDao<ClassRoomEntity>() {

    open suspend fun replaceAllClass(entites: List<ClassRoomEntity>) {
        deleteAllClass()
        insertAll(entites)
    }

    @Query("SELECT * FROM class_room ORDER BY id ASC")
    abstract suspend fun selectAllClass(): List<ClassRoomEntity>

    @Query("DELETE FROM class_room")
    abstract suspend fun deleteAllClass(): Int

    @Query("SELECT * FROM class_room ORDER BY id ASC")
    abstract fun selectALlClassAsFlow(): Flow<List<ClassRoomEntity>>

    @Query("SELECT * FROM class_room WHERE kelas = :classRoom")
    abstract suspend fun selectClassRoomId(classRoom: String): ClassRoomEntity

}