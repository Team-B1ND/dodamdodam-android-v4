package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.request.auth.JoinRequest
import kr.hs.dgsw.smartschool.data.network.request.auth.LoginRequest
import kr.hs.dgsw.smartschool.domain.param.auth.JoinParam
import kr.hs.dgsw.smartschool.domain.param.auth.LoginParam

fun JoinParam.toRequest(): JoinRequest = JoinRequest(
    email = this.email,
    grade = this.grade,
    id = this.id,
    name = this.name,
    number = this.number,
    phone = this.phone,
    pw = this.encryptedPw,
    room = this.room
)

fun LoginParam.toRequest(): LoginRequest = LoginRequest(
    id = this.id,
    pw = this.encryptedPw,
    encryption = this.encryption
)
