package kr.hs.dgsw.smartschool.domain.usecase.eveningstudy

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.repository.EveningStudyRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DeleteEveningStudy @Inject constructor(
    private val eveningStudyRepository: EveningStudyRepository
) : UseCase<Int, String>() {

    override fun invoke(params: Int): Flow<Resource<String>> = execute {
        eveningStudyRepository.deleteEveningStudy(params)
    }
}
