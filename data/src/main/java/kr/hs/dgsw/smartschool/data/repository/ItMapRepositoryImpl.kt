package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.ItMapDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.domain.model.itmap.Company
import kr.hs.dgsw.smartschool.domain.repository.ItMapRepository
import javax.inject.Inject

class ItMapRepositoryImpl @Inject constructor(
    private val itMapDataSource: ItMapDataSource
) : ItMapRepository {

    override suspend fun getAllCompanies(): List<Company> {
        return itMapDataSource.getAllCompanies().map { companyResponse -> companyResponse.toModel() }
    }

    override suspend fun getCompanyById(id: Int): Company {
        return itMapDataSource.getCompanyById(id).toModel()
    }
}
