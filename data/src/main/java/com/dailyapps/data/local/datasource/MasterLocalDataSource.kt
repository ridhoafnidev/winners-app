package com.dailyapps.data.local.datasource

import com.dailyapps.data.local.room.dao.ClassRoomDao
import com.dailyapps.data.local.room.dao.SchoolYearDao
import com.dailyapps.data.local.room.dao.StudentDao
import com.dailyapps.data.local.room.dao.TeacherDao
import com.dailyapps.data.local.room.entity.ClassRoomEntity
import com.dailyapps.data.local.room.entity.SchoolYearEntity
import com.dailyapps.data.local.room.entity.StudentEntity
import com.dailyapps.data.local.room.entity.TeacherEntity
import com.dailyapps.data.utils.DataMapper.toDomainClassRoom
import com.dailyapps.data.utils.DataMapper.toDomainClassRooms
import com.dailyapps.data.utils.DataMapper.toDomainSchoolYear
import com.dailyapps.data.utils.DataMapper.toDomainSchoolYears
import com.dailyapps.domain.utils.Resource
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MasterLocalDataSource @Inject constructor(
    private val schoolYearDao: SchoolYearDao,
    private val classRoomDao: ClassRoomDao,
    private val teacherDao: TeacherDao,
    private val studentDao: StudentDao,
) {
    // region School Year

    suspend fun deleteAllSchoolYears() = schoolYearDao.deleteAllSchoolYear()

    suspend fun replaceAllSchoolYears(schoolYearEntities: List<SchoolYearEntity>) = schoolYearDao.replaceAllSchoolYears(schoolYearEntities);

    fun getMasterSchoolYears() = schoolYearDao.selectALlSchoolYearAsFlow().map { schoolYears ->
        Resource.Success(schoolYears.toDomainSchoolYears())
    }

    suspend fun getSchoolYearId(year: String, sem: String) =
        Resource.Success(schoolYearDao.selectSchoolYearId(year, sem).toDomainSchoolYear())

    //endregion
    //region Class Room

    suspend fun deleteAllClassRoom() = classRoomDao.deleteAllClass()

    suspend fun replaceAllClassRoom(entities: List<ClassRoomEntity>) = classRoomDao.replaceAllClass(entities);

    fun getAllClassRoom() = classRoomDao.selectALlClassAsFlow().map { classRoom ->
        Resource.Success(classRoom.toDomainClassRooms())
    }

    //endregion
    //region Teacher

    suspend fun deleteAllTeacher() = teacherDao.deleteAllTeacher()

    suspend fun replaceAllTeacher(entities: List<TeacherEntity>) = teacherDao.replaceAllTeacher(entities);

    suspend fun getClassRoomId(classRoom: String) =
        Resource.Success(classRoomDao.selectClassRoomId(classRoom).toDomainClassRoom())

    //endregion
    //region student

    suspend fun replaceAllStudents(entities: List<StudentEntity>) = studentDao.replaceAllStudent(entities);

    suspend fun getStudentClass() = studentDao.selectAllStudent()

    //endregion
}