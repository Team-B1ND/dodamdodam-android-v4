package kr.hs.dgsw.smartschool.domain.usecase.out

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetOutByDate @Inject constructor(
    private val outRepository: OutRepository
) : BaseUseCase<String, List<OutItem>>() {

    override fun invoke(params: String): Flow<Resource<List<OutItem>>> = execute {
        outRepository.getOutByDate(params)
    }

}
