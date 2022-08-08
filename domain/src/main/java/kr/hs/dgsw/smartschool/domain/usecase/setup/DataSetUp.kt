package kr.hs.dgsw.smartschool.domain.usecase.setup

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.DataSetUpRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DataSetUp @Inject constructor(
    val repository: DataSetUpRepository
) : BaseUseCase<Unit, String>() {
    override operator fun invoke(params: Unit): Flow<Resource<String>> = execute {
         repository.setUpData()
         "데이터 업데이트에 성공했습니다."
    }
}