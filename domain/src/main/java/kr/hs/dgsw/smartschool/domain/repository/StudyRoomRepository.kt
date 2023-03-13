package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.param.studyroom.DefaultStudyRoomByTypeRequest
import kr.hs.dgsw.smartschool.domain.param.studyroom.DefaultStudyRoomRequest
import kr.hs.dgsw.smartschool.domain.param.studyroom.StudyRoomRequest

interface StudyRoomRepository {

    suspend fun applyStudyRoom(request: StudyRoomRequest): String

    suspend fun modifyAppliedStudyRoom(request: StudyRoomRequest): String

    suspend fun getStudyRoomById(id: Int): StudyRoom

    suspend fun cancelStudyRoom(id: Int): String

    suspend fun getDefaultStudyRoom(): List<DefaultStudyRoom>

    suspend fun createDefaultStudyRoom(request: DefaultStudyRoomRequest): String

    suspend fun createDefaultStudyRoomByWeekType(request: DefaultStudyRoomByTypeRequest): String

    suspend fun getMyStudyRoom(): List<StudyRoom>
}
