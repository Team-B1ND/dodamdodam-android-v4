package kr.hs.dgsw.smartschool.dodamdodam.features.studyroom

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom

data class GetMyStudyRoomState(
    val myStudyRooms: List<StudyRoom> = emptyList(),
    val error: String = ""
)
