package kr.hs.dgsw.smartschool.dodamdodam.features.location

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom

data class GetMyLocationState(
    val myLocations: List<StudyRoom> = emptyList(),
    val error: String = ""
)
