package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

interface ClassInfoRepository {
    suspend fun getAllClassInfo(): List<Classroom>
    suspend fun getClassInfo(idx: Int): Classroom
}
