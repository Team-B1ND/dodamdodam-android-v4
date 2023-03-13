package kr.hs.dgsw.smartschool.data.datasource

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.StudyRoomRemote
import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.param.studyroom.DefaultStudyRoomByTypeRequest
import kr.hs.dgsw.smartschool.domain.param.studyroom.DefaultStudyRoomRequest
import kr.hs.dgsw.smartschool.domain.param.studyroom.StudyRoomRequest
import javax.inject.Inject

class StudyRoomDataSource @Inject constructor(
    override val remote: StudyRoomRemote,
    override val cache: Any
) : BaseDataSource<StudyRoomRemote, Any> {

    suspend fun applyStudyRoom(request: StudyRoomRequest): String = remote.applyStudyRoom(request)

    suspend fun modifyAppliedStudyRoom(request: StudyRoomRequest): String = remote.modifyAppliedStudyRoom(request)

    suspend fun getStudyRoomById(id: Int): StudyRoom = remote.getStudyRoomById(id)

    suspend fun cancelStudyRoom(id: Int): String = remote.cancelStudyRoom(id)

    suspend fun getDefaultStudyRoom(): List<DefaultStudyRoom> = remote.getDefaultStudyRoom()

    suspend fun createDefaultStudyRoom(request: DefaultStudyRoomRequest): String =
        remote.createDefaultStudyRoom(request)

    suspend fun createDefaultStudyRoomByWeekType(request: DefaultStudyRoomByTypeRequest): String =
        remote.createDefaultStudyRoomByWeekType(request)

    suspend fun getMyStudyRoom(): List<StudyRoom?> {
        Log.d("InRemote", "getMyStudyRoom: go")
        return remote.getMyStudyRoom()
    }
}
