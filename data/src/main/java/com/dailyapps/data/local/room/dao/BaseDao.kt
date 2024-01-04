package com.dailyapps.data.local.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Suppress("UnnecessaryAbstractClass")
abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun inserts(vararg item: T): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(items: List<T>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updates(vararg item: T): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateAll(items: List<T>): Int

    @Delete
    abstract fun deletes(vararg item: T): Int

    @Delete
    abstract fun deleteAll(items: List<T>): Int
}