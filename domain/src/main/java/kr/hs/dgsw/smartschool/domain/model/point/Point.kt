package kr.hs.dgsw.smartschool.domain.model.point

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.member.StudentId
import kr.hs.dgsw.smartschool.domain.model.member.TeacherId

data class Point(
    val log : List<PointLog>
)
