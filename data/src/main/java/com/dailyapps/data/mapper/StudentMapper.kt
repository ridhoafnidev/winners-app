package com.dailyapps.data.mapper

import com.dailyapps.apiresponse.BaseResponse
import com.dailyapps.apiresponse.StudentApiResponse
import com.dailyapps.data.utils.Mapper
import com.dailyapps.entity.Student
import javax.inject.Inject

class StudentMapper @Inject constructor(): Mapper<BaseResponse<List<StudentApiResponse>>, List<Student>> {
    override fun mapFromApiResponse(type: BaseResponse<List<StudentApiResponse>>): List<Student> {
        return type.data.map { data ->
            Student(
                nama = data.nama,
                noHp = data.noHp,
                foto = data.foto,
                updatedAt = data.updatedAt,
                nisn = data.nisn,
                createdAt = data.createdAt,
                id = data.id,
                jenisKelamin = data.jenisKelamin,
                deletedAt = data.deletedAt,
                alamat = data.alamat
            )
        }
    }
}