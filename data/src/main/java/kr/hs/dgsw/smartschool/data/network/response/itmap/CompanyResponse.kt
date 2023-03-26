package kr.hs.dgsw.smartschool.data.network.response.itmap

import kr.hs.dgsw.smartschool.domain.model.itmap.ItMapUser

data class CompanyResponse(
    val address: String,
    val companyPlaceId: String,
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val symbolLogo: String?,
    val textLogo: String?,
    val users: List<ItMapUser>?
)
