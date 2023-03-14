package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class CancelStudyRoom @Inject constructor(
    private val studyRoomRepository: StudyRoomRepository
) : UseCase<Int, String>() {

    override fun invoke(params: Int): Flow<Resource<String>> = execute {
        studyRoomRepository.cancelStudyRoom(params)
    }
}
