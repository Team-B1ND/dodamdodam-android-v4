package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyStudyRoom @Inject constructor(
    val repository: StudyRoomRepository
) : BaseUseCase<String, List<StudyRoom>>() {

    override fun invoke(params: String): Flow<Resource<List<StudyRoom>>> = execute {
        repository.getMyStudyRoom()
    }

}


