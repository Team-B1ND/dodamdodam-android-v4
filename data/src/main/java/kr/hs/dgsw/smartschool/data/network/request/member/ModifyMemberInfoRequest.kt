package kr.hs.dgsw.smartschool.data.network.request.member

data class ModifyMemberInfoRequest(
    var email: String,
    var imageUrl: String,
    var phone: String,
)
