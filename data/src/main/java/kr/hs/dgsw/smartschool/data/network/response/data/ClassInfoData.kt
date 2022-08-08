package kr.hs.dgsw.smartschool.data.network.response.data

import kr.hs.dgsw.smartschool.domain.model.classinfo.ClassInfo

data class ClassInfoData(
    val classrooms: List<ClassInfo>
)
