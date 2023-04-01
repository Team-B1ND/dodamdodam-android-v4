package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.data.network.response.lostpound.LostFoundResponse
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.param.lostfound.LostFoundDataParam

fun LostFoundResponse.toModel(): LostFound = LostFound(
    content = this.content,
    createAt = this.createAt,
    idx = this.idx,
    member = this.member.toModel(),
    place = this.place,
    title = this.title,
    type = this.type,
    image = this.image
)

fun LostFoundDataParam.toRequest(): LostFoundDataRequest = LostFoundDataRequest(
    content = this.content,
    lostFoundId = this.lostFoundId,
    picture = this.picture,
    place = this.place,
    title = this.title,
    type = this.type
)
