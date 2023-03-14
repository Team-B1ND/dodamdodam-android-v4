package kr.hs.dgsw.smartschool.domain.usecase.time

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable
import kr.hs.dgsw.smartschool.domain.repository.TimeRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetAllTime @Inject constructor(
    val repository: TimeRepository
) : NoParamUseCase<List<TimeTable>>() {
    override fun invoke(): Flow<Resource<List<TimeTable>>> = execute {
        repository.getAllTime()
    }
}
