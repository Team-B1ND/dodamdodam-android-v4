package kr.hs.dgsw.smartschool.domain.model.member

data class HistoryMember(
    val id: String,
    val name: String,
    val profileImage: String?
) {
    constructor() : this("" ,"", "")
}