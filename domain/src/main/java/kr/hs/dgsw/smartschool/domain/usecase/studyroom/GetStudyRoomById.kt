package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetStudyRoomById @Inject constructor(
    private val repository: StudyRoomRepository
) : BaseUseCase<Int, StudyRoom>() {

    override fun invoke(params: Int): Flow<Resource<StudyRoom>> = execute {
        repository.getStudyRoomById(params)
    }
}
