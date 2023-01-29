package kr.hs.dgsw.smartschool.domain.usecase.itmap

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.model.itmap.Company
import kr.hs.dgsw.smartschool.domain.repository.ItMapRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetAllCompanies @Inject constructor(
    private val itMapRepository: ItMapRepository
) : NoParamUseCase<List<Company>>() {

    override fun invoke(): Flow<Resource<List<Company>>> = execute {
        itMapRepository.getAllCompanies()
    }
}
