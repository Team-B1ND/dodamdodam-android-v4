package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ApplyStudyRoom @Inject constructor(
    val repository: StudyRoomRepository
) : UseCase<ApplyStudyRoom.Params, String>() {

    override operator fun invoke(params: Params): Flow<Resource<String>> = execute {
        repository.applyStudyRoom(
            params.studyRoomList
        )
    }

    data class Params(
        val studyRoomList: List<RequestStudyRoom>
    ) {
        data class RequestStudyRoom(
            val placeId: Int,
            val timeTableId: Int
        )
    }
}
