package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.request.studyroom.DefaultStudyRoomRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class CreateDefaultStudyRoom @Inject constructor(
    private val repository: StudyRoomRepository
) : BaseUseCase<CreateDefaultStudyRoom.Params, String>() {

    override fun invoke(params: Params): Flow<Resource<String>> = execute {
        repository.createDefaultStudyRoom(
            DefaultStudyRoomRequest(
                day = params.day,
                defaultStudyRooms = params.defaultStudyRooms
            )
        )
    }

    data class Params(
        val day: String,
        val defaultStudyRooms: List<DefaultStudyRoomRequest.DefaultStudyRoom>
    )
}
