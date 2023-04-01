package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.param.studyroom.DefaultStudyRoomByTypeParam
import kr.hs.dgsw.smartschool.domain.param.studyroom.DefaultStudyRoomParam
import kr.hs.dgsw.smartschool.domain.param.studyroom.StudyRoomParam

interface StudyRoomRepository {

    suspend fun applyStudyRoom(request: StudyRoomParam): String

    suspend fun modifyAppliedStudyRoom(request: StudyRoomParam): String

    suspend fun getStudyRoomById(id: Int): StudyRoom

    suspend fun cancelStudyRoom(id: Int): String

    suspend fun getDefaultStudyRoom(): List<DefaultStudyRoom>

    suspend fun createDefaultStudyRoom(request: DefaultStudyRoomParam): String

    suspend fun createDefaultStudyRoomByWeekType(request: DefaultStudyRoomByTypeParam): String

    suspend fun getMyStudyRoom(): List<StudyRoom>
}
