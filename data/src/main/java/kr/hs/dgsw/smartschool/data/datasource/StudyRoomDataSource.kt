package kr.hs.dgsw.smartschool.data.datasource

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.StudyRoomRemote
import kr.hs.dgsw.smartschool.data.network.request.studyroom.DefaultStudyRoomByTypeRequest
import kr.hs.dgsw.smartschool.data.network.request.studyroom.DefaultStudyRoomRequest
import kr.hs.dgsw.smartschool.data.network.request.studyroom.DefaultStudyRoomsRequest
import kr.hs.dgsw.smartschool.data.network.request.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.data.network.response.studyroom.StudyRoomResponse
import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.ApplyStudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.CreateDefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.ModifyAppliedStudyRoom
import javax.inject.Inject

class StudyRoomDataSource @Inject constructor(
    override val remote: StudyRoomRemote,
    override val cache: Any
) : BaseDataSource<StudyRoomRemote, Any> {

    suspend fun applyStudyRoom(studyRoomList: List<ApplyStudyRoom.Params.RequestStudyRoom>): String = remote.applyStudyRoom(StudyRoomRequest(studyRoomList.map { studyRoom -> StudyRoomRequest.RequestStudyRoom(studyRoom.placeId, studyRoom.timeTableId) }))

    suspend fun modifyAppliedStudyRoom(studyRoomList: List<ModifyAppliedStudyRoom.Params.RequestStudyRoom>): String = remote.modifyAppliedStudyRoom(StudyRoomRequest(studyRoomList.map { studyRoom -> StudyRoomRequest.RequestStudyRoom(studyRoom.placeId, studyRoom.timeTableId) }))

    suspend fun getStudyRoomById(id: Int): StudyRoomResponse = remote.getStudyRoomById(id)

    suspend fun cancelStudyRoom(id: Int): String = remote.cancelStudyRoom(id)

    suspend fun getDefaultStudyRoom(): List<DefaultStudyRoom> = remote.getDefaultStudyRoom()

    suspend fun createDefaultStudyRoom(day: String, defaultStudyRooms: List<CreateDefaultStudyRoom.Params.DefaultStudyRoom>): String =
        remote.createDefaultStudyRoom(DefaultStudyRoomRequest(day, defaultStudyRooms.map { defaultStudyRoom -> DefaultStudyRoomsRequest(defaultStudyRoom.placeId, defaultStudyRoom.timeTableId) }))

    suspend fun createDefaultStudyRoomByWeekType(placeId: Int, timeTableId: Int, type: Int): String =
        remote.createDefaultStudyRoomByWeekType(DefaultStudyRoomByTypeRequest(placeId, timeTableId, type))

    suspend fun getMyStudyRoom(): List<StudyRoomResponse?> {
        Log.d("InRemote", "getMyStudyRoom: go")
        return remote.getMyStudyRoom()
    }
}
