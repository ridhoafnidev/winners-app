package com.dailyapps.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dailyapps.data.local.room.dao.ClassRoomDao
import com.dailyapps.data.local.room.dao.SchoolYearDao
import com.dailyapps.data.local.room.dao.StudentDao
import com.dailyapps.data.local.room.dao.TeacherDao
import com.dailyapps.data.local.room.dao.UserDao
import com.dailyapps.data.local.room.entity.ClassRoomEntity
import com.dailyapps.data.local.room.entity.SchoolYearEntity
import com.dailyapps.data.local.room.entity.StudentEntity
import com.dailyapps.data.local.room.entity.TeacherEntity
import com.dailyapps.data.local.room.entity.UserEntity

@Database(entities = [UserEntity::class, SchoolYearEntity::class, ClassRoomEntity::class, TeacherEntity::class, StudentEntity::class], version = 2, exportSchema = false)
abstract class ISekolahDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun schoolYearDao(): SchoolYearDao

    abstract fun classDao(): ClassRoomDao

    abstract fun teacherDao(): TeacherDao

    abstract fun studentDao(): StudentDao
}