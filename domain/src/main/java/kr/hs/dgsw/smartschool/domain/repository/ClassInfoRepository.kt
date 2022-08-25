package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.classinfo.ClassInfo

interface ClassInfoRepository {
    suspend fun getAllClassInfo(): List<ClassInfo>
    suspend fun getClassInfo(idx: Int): ClassInfo
}
