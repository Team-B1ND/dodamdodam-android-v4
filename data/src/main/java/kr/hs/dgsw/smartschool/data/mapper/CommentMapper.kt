package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.lostpound.CommentResponse
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment

fun CommentResponse.toModel(): Comment = Comment(
    idx = this.idx,
    comment = this.comment,
    lostFound = this.lostFound,
    member = this.member
)
