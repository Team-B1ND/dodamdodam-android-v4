package kr.hs.dgsw.smartschool.domain.usecase.time

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.repository.TimeRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetStartTime @Inject constructor(
    val repository: TimeRepository
) : NoParamUseCase<String>() {
    override operator fun invoke(): Flow<Resource<String>> = execute {
        repository.getStartTime()
    }
}
