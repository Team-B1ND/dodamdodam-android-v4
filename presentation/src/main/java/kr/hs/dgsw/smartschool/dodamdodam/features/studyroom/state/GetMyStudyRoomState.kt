package kr.hs.dgsw.smartschool.dodamdodam.features.studyroom.state

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom

data class GetMyStudyRoomState(
    val isUpdate: Boolean = false,
    val myStudyRooms: List<StudyRoom> = emptyList(),
    val error: String = ""
)
