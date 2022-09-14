package kr.hs.dgsw.smartschool.dodamdodam.features.itmap.detail.state

import kr.hs.dgsw.smartschool.domain.model.itmap.Company

data class GetCompanyByIdState(
    val company: Company? = null,
    val error: String = "",

)
