package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.request.member.ModifyMemberInfoRequest
import kr.hs.dgsw.smartschool.domain.param.member.ModifyMemberInfoParam

fun ModifyMemberInfoParam.toRequest(): ModifyMemberInfoRequest = ModifyMemberInfoRequest(
    email = this.email,
    imageUrl = this.imageUrl,
    phone = this.phone
)
