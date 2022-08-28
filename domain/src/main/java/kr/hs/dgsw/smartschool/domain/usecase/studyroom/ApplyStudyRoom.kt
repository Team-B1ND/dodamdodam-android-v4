package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.request.StudyRoomRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ApplyStudyRoom @Inject constructor(
    val repository: StudyRoomRepository
) : BaseUseCase<ApplyStudyRoom.Params, String>() {

    override operator fun invoke(params: Params): Flow<Resource<String>> = execute {
        repository.modifyAppliedStudyRoom(
            request = StudyRoomRequest(
                studyRoomList = params.studyRoomList
            )
        )
    }

    data class Params(
        val studyRoomList: List<StudyRoomRequest.RequestStudyRoom>
    )
}
