package kr.hs.dgsw.smartschool.data.network.response.data

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

data class ClassInfoData(
    val classrooms: List<Classroom>
)
