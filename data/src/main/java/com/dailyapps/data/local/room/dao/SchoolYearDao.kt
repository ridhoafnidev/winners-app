package com.dailyapps.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.dailyapps.data.local.room.entity.SchoolYearEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SchoolYearDao: BaseDao<SchoolYearEntity>() {

    open suspend fun replaceAllSchoolYears(schoolYearEntities: List<SchoolYearEntity>) {
        deleteAllSchoolYear()
        insertAll(schoolYearEntities)
    }

    @Query("SELECT * FROM school_year WHERE semester = :sem AND tahunAjaran= :year")
    abstract suspend fun selectSchoolYearId(year: String, sem: String): SchoolYearEntity

    @Query("DELETE FROM school_year")
    abstract suspend fun deleteAllSchoolYear(): Int

    @Query("SELECT * FROM school_year ORDER BY id ASC")
    abstract fun selectALlSchoolYearAsFlow(): Flow<List<SchoolYearEntity>>

}