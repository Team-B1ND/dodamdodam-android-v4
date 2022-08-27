package kr.hs.dgsw.smartschool.domain.usecase.location

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.repository.LocationRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyLocation @Inject constructor(
    val repository: LocationRepository
) : BaseUseCase<String, List<StudyRoom>>() {
    override fun invoke(params: String): Flow<Resource<List<StudyRoom>>> = execute {
        repository.getMyLocation(params)
    }
}
