package kr.hs.dgsw.smartschool.domain.model.bus

data class BusMember(
    val idx: Integer, // busMember idx
    val busIdx: Integer, // bus idx
    val studentIdx: Integer, // student idx
    val memberId: String, // member id
    val name: String, // 회원 이름
    val email: String, // 회원 이메일
    val phone: String // 회원 전화번호
)
