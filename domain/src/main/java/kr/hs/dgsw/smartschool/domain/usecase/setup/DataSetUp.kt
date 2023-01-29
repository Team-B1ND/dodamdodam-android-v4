package kr.hs.dgsw.smartschool.domain.usecase.setup

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.repository.DataSetUpRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DataSetUp @Inject constructor(
    val repository: DataSetUpRepository
) : NoParamUseCase<String>() {
    override operator fun invoke(): Flow<Resource<String>> = execute {
        repository.setUpData()
    }
}
