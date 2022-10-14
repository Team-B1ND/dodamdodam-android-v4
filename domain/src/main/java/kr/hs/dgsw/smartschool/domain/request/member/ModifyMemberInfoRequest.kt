package kr.hs.dgsw.smartschool.domain.request.member

data class ModifyMemberInfoRequest(
    var email: String,
    var imageUrl: String,
    var phone: String,
)
