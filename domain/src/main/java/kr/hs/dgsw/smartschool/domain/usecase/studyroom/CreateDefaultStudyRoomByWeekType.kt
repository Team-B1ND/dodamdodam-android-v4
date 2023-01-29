package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.request.studyroom.DefaultStudyRoomByTypeRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class CreateDefaultStudyRoomByWeekType @Inject constructor(
    private val repository: StudyRoomRepository
) : UseCase<CreateDefaultStudyRoomByWeekType.Params, String>() {

    override fun invoke(params: Params): Flow<Resource<String>> = execute {
        repository.createDefaultStudyRoomByWeekType(
            DefaultStudyRoomByTypeRequest(
                placeId = params.placeId,
                timeTableId = params.timeTableId,
                type = params.type
            )
        )
    }

    data class Params(
        val placeId: Int,
        val timeTableId: Int,
        val type: Int
    )
}
