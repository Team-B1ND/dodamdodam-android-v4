package kr.hs.dgsw.smartschool.domain.model.itmap

data class ItMapUser(
    val field: String,
    val generation: Int,
    val githubId: String,
    val id: Int,
    val image: String,
    val info: String,
    val name: String
)
