package kr.hs.dgsw.smartschool.dodamdodam.features.itmap.state

import kr.hs.dgsw.smartschool.domain.model.itmap.Company

data class GetAllCompaniesState(
    val isUpdate: Boolean = false,
    val companies: List<Company> = emptyList(),
    val error: String = "",

)