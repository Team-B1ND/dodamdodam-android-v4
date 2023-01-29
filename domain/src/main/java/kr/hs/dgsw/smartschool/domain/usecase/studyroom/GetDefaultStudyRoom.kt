package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.noParamBaseUseCase
import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetDefaultStudyRoom @Inject constructor(
    private val repository: StudyRoomRepository
) : noParamBaseUseCase<List<DefaultStudyRoom>>() {

    override fun invoke(): Flow<Resource<List<DefaultStudyRoom>>> = execute {
        repository.getDefaultStudyRoom()
    }
}
