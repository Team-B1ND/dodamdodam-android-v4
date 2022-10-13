package kr.hs.dgsw.smartschool.domain.model.itmap

data class Company(
    val address: String,
    val companyPlaceId: String,
    val id: Int,
    val name: String,
    val users: List<ItMapUser>
)
