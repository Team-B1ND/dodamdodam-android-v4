package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.ItMapApi
import kr.hs.dgsw.smartschool.domain.model.itmap.Company
import javax.inject.Inject

class ItMapRemote @Inject constructor(
    override val api: ItMapApi
) : BaseRemote<ItMapApi>() {

    suspend fun getAllCompanies(): List<Company> =
        api.getAllCompanies().data

    suspend fun getCompanyById(id: Int): Company =
        api.getCompanyById(id).data

}