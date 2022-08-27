package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.request.DefaultLocationRequest
import kr.hs.dgsw.smartschool.domain.request.StudyRoomRequest

interface LocationRepository {

    suspend fun postLocation(studyRoomRequest: StudyRoomRequest): String

    suspend fun getMyLocation(date: String): List<StudyRoom>

    suspend fun getDefaultLocation(dayOfWeek: Int): List<DefaultStudyRoom>

    suspend fun postDefaultLocation(request: DefaultLocationRequest): String

    suspend fun putAllLocation(request: StudyRoomRequest): String

    suspend fun putLocation(idx: Int, placeIdx: Location): String

    suspend fun deleteLocation(idx: Int): String
}
