package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.param.studyroom.StudyRoomParam
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ModifyAppliedStudyRoom @Inject constructor(
    val repository: StudyRoomRepository
) : UseCase<ModifyAppliedStudyRoom.Params, String>() {

    override operator fun invoke(params: Params): Flow<Resource<String>> = execute {
        repository.modifyAppliedStudyRoom(
            request = StudyRoomParam(
                studyRoomList = params.studyRoomList
            )
        )
    }

    data class Params(
        val studyRoomList: List<StudyRoomParam.RequestStudyRoom>
    )
}
