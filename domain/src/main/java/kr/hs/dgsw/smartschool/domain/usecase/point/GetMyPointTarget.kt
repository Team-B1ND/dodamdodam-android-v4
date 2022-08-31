package kr.hs.dgsw.smartschool.domain.usecase.point

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyPointTarget @Inject constructor(
    val repository: PointRepository
) : BaseUseCase<Int, MyTargetPoint>() {
    override operator fun invoke(params: Int): Flow<Resource<MyTargetPoint>> = execute {
        repository.getMyPointTarget(params)
    }
}
