package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.LocationRemote
import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRooms
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.request.DefaultLocationRequest
import kr.hs.dgsw.smartschool.domain.request.StudyRoomRequest
import javax.inject.Inject

class LocationDataSource @Inject constructor(
    override val remote: LocationRemote,
    override val cache: Any
) : BaseDataSource<LocationRemote, Any> {

    suspend fun postLocation(studyRoomRequest: StudyRoomRequest): String =
        remote.postLocation(studyRoomRequest)

    suspend fun getMyLocation(date: String): List<StudyRoom?> = remote.getMyLocation(date)

    suspend fun getLocation(): List<StudyRooms> = remote.getLocation()

    suspend fun getDefaultLocation(dayOfWeek: Int): List<DefaultStudyRoom> = remote.getDefaultLocation(dayOfWeek)

    suspend fun postDefaultLocation(request: DefaultLocationRequest): String = remote.postDefaultLocation(request)

    suspend fun putAllLocation(request: StudyRoomRequest): String = remote.putAllLocation(request)

    suspend fun putLocation(idx: Int, placeIdx: Location): String = remote.putLocation(idx, placeIdx)

    suspend fun deleteLocation(idx: Int): String = remote.deleteLocation(idx)

    suspend fun checkLocation(idx: Int): String = remote.checkLocation(idx)

    suspend fun unCheckLocation(idx: Int): String = remote.unCheckLocation(idx)

    suspend fun postLocationControl(student: Student, locations: List<StudyRoom>): String =
        remote.postLocationControl(LocationControlRequest(student, locations))
}
