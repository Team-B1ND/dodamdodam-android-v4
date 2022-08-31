package kr.hs.dgsw.smartschool.data.network.remote

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.StudyRoomApi
import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.request.DefaultStudyRoomByTypeRequest
import kr.hs.dgsw.smartschool.domain.request.DefaultStudyRoomRequest
import kr.hs.dgsw.smartschool.domain.request.StudyRoomRequest

class StudyRoomRemote(override val api: StudyRoomApi) : BaseRemote<StudyRoomApi>() {

    suspend fun applyStudyRoom(request: StudyRoomRequest): String =
        api.applyStudyRoom(request).message

    suspend fun modifyAppliedStudyRoom(request: StudyRoomRequest): String =
        api.modifyAppliedStudyRoom(request).message

    suspend fun getStudyRoomById(id: Int): StudyRoom =
        api.getStudyRoomById(id).data

    suspend fun cancelStudyRoom(id: Int): String =
        api.cancelStudyRoom(id).message

    suspend fun getDefaultStudyRoom(): List<DefaultStudyRoom> =
        api.getDefaultStudyRoom().data.defaultStudyRooms

    suspend fun createDefaultStudyRoom(request: DefaultStudyRoomRequest): String =
        api.createDefaultStudyRoom(request).message

    suspend fun createDefaultStudyRoomByWeekType(request: DefaultStudyRoomByTypeRequest): String =
        api.createDefaultStudyRoomByWeekType(request).message

    suspend fun getMyStudyRoom(): List<StudyRoom?> {
        Log.d("TestTest", "getMyStudyRoom: InRemote")
        return api.getMyStudyRoom().data
    }
}
