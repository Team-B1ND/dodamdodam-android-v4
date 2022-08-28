package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.StudyRoomDataSource
import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.request.DefaultStudyRoomByTypeRequest
import kr.hs.dgsw.smartschool.domain.request.DefaultStudyRoomRequest
import kr.hs.dgsw.smartschool.domain.request.StudyRoomRequest
import javax.inject.Inject

class StudyRoomRepositoryImpl @Inject constructor(
    private val studyRoomDataSource: StudyRoomDataSource
) : StudyRoomRepository {

    override suspend fun applyStudyRoom(request: StudyRoomRequest): String {
        return studyRoomDataSource.applyStudyRoom(request)
    }

    override suspend fun getMyStudyRoom(): List<StudyRoom> {
        return studyRoomDataSource.getMyStudyRoom()
    }

    override suspend fun modifyAppliedStudyRoom(request: StudyRoomRequest): String {
        return modifyAppliedStudyRoom(request)
    }

    override suspend fun getStudyRoomById(id: Int): StudyRoom {
        return getStudyRoomById(id)
    }

    override suspend fun getDefaultStudyRoom(): List<DefaultStudyRoom> {
        return getDefaultStudyRoom()
    }

    override suspend fun createDefaultStudyRoom(request: DefaultStudyRoomRequest): String {
        return createDefaultStudyRoom(request)
    }

    override suspend fun createDefaultStudyRoomByWeekType(request: DefaultStudyRoomByTypeRequest): String {
        return createDefaultStudyRoomByWeekType(request)
    }

}
