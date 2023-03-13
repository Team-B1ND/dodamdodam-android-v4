package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.lostpound.LostFoundResponse
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound

fun LostFoundResponse.toModel(): LostFound= LostFound(
    content = this.content,
    createAt = this.createAt,
    idx = this.idx,
    member = this.member.toModel(),
    place = this.place,
    title = this.title,
    type = this.type,
    image = this.image
)