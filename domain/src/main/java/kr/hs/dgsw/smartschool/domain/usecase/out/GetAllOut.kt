package kr.hs.dgsw.smartschool.domain.usecase.out

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.base.noParamBaseUseCase
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetAllOut @Inject constructor(
    private val outRepository: OutRepository
) : noParamBaseUseCase<List<OutItem>>() {

    override fun invoke(): Flow<Resource<List<OutItem>>> = execute {
        outRepository.getAllOut()
    }
}
