package kr.hs.dgsw.smartschool.domain.usecase.itmap

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.itmap.Company
import kr.hs.dgsw.smartschool.domain.repository.ItMapRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetCompanyById @Inject constructor(
    private val itMapRepository: ItMapRepository
) : UseCase<Int, Company>() {

    override fun invoke(params: Int): Flow<Resource<Company>> = execute {
        itMapRepository.getCompanyById(params)
    }
}
