package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.ItMapRemote
import kr.hs.dgsw.smartschool.domain.model.itmap.Company
import javax.inject.Inject

class ItMapDataSource @Inject constructor(
    override val remote: ItMapRemote,
    override val cache: Any
) : BaseDataSource<ItMapRemote, Any> {

    suspend fun getAllCompanies(): List<Company> =
        remote.getAllCompanies()

    suspend fun getCompanyById(id: Int): Company =
        remote.getCompanyById(id)

}