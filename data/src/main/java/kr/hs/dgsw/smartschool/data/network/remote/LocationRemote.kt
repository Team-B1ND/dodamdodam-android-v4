package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.StudyRoomApi
import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRooms
import kr.hs.dgsw.smartschool.domain.request.DefaultLocationRequest
import kr.hs.dgsw.smartschool.domain.request.StudyRoomRequest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LocationRemote(override val api: StudyRoomApi) : BaseRemote<StudyRoomApi>() {

    private val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    suspend fun postLocation(studyRoomRequest: StudyRoomRequest): String =
        api.applyStudyRoom(studyRoomRequest).message

    suspend fun getMyLocation(date: String): List<StudyRoom?> =
        api.getMyStudyRoom(date).data.locations

    suspend fun getLocation(): List<StudyRooms> =
        api.getStudyRooms(format.format(Date())).data

    suspend fun getDefaultLocation(day: Int): List<DefaultStudyRoom> =
        api.getDefaultStudyRoom(day).data.defaultLocations.sortedBy { it.timeTableIdx }

    suspend fun postDefaultLocation(request: DefaultLocationRequest): String =
        api.createDefaultStudyRoom(request).message

    suspend fun putAllLocation(request: StudyRoomRequest): String =
        api.modifyAppliedStudyRoom(request).message

    suspend fun putLocation(idx: Int, placeIdx: Location): String =
        api.putLocation(idx, placeIdx).message

    suspend fun deleteLocation(idx: Int): String =
        api.deleteLocation(idx).message

    suspend fun checkLocation(idx: Int): String =
        api.checkLocation(idx).message

    suspend fun unCheckLocation(idx: Int): String =
        api.unCheckLocation(idx).message

    suspend fun postLocationControl(request: LocationControlRequest): String =
        api.postLocationControl(request).message
}
