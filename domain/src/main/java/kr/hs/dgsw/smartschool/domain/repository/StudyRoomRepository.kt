package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.ApplyStudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.CreateDefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.ModifyAppliedStudyRoom

interface StudyRoomRepository {

    suspend fun applyStudyRoom(studyRoomList: List<ApplyStudyRoom.Params.RequestStudyRoom>): String

    suspend fun modifyAppliedStudyRoom(studyRoomList: List<ModifyAppliedStudyRoom.Params.RequestStudyRoom>): String

    suspend fun getStudyRoomById(id: Int): StudyRoom

    suspend fun cancelStudyRoom(id: Int): String

    suspend fun getDefaultStudyRoom(): List<DefaultStudyRoom>

    suspend fun createDefaultStudyRoom(day: String, defaultStudyRooms: List<CreateDefaultStudyRoom.Params.DefaultStudyRoom>): String

    suspend fun createDefaultStudyRoomByWeekType(placeId: Int, timeTableId: Int, type: Int): String

    suspend fun getMyStudyRoom(): List<StudyRoom>
}
