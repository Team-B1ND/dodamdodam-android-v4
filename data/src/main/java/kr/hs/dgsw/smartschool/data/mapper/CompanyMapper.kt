package kr.hs.dgsw.smartschool.data.mapper


import kr.hs.dgsw.smartschool.data.network.response.itmap.CompanyResponse
import kr.hs.dgsw.smartschool.domain.model.itmap.Company

fun CompanyResponse.toModel(): Company = Company(
    address = this.address,
    companyPlaceId = this.companyPlaceId,
    id = this.id,
    latitude = this.latitude,
    longitude = this.longitude,
    name = this.name,
    symbolLogo = this.symbolLogo,
    textLogo = this.textLogo,
    users = this.users
)
