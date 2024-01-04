package com.dailyapps.data.module

import android.content.Context
import androidx.room.Room
import com.dailyapps.data.local.room.ISekolahDatabase
import com.dailyapps.data.local.room.dao.ClassRoomDao
import com.dailyapps.data.local.room.dao.SchoolYearDao
import com.dailyapps.data.local.room.dao.StudentDao
import com.dailyapps.data.local.room.dao.TeacherDao
import com.dailyapps.data.local.room.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ISekolahDatabase = Room.databaseBuilder(
        context,
        ISekolahDatabase::class.java, "ISekolah.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideUserDao(database: ISekolahDatabase): UserDao = database.userDao()

    @Provides
    fun provideSchoolYearDao(database: ISekolahDatabase): SchoolYearDao = database.schoolYearDao()

    @Provides
    fun provideClassDao(database: ISekolahDatabase): ClassRoomDao = database.classDao()

    @Provides
    fun provideTeacherDao(database: ISekolahDatabase): TeacherDao = database.teacherDao()
    @Provides
    fun provideStudentDao(database: ISekolahDatabase): StudentDao = database.studentDao()

}