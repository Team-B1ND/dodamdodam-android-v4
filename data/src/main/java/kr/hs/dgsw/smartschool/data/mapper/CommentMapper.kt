package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.data.network.request.lostfound.ModifyCommentRequest
import kr.hs.dgsw.smartschool.data.network.response.lostpound.CommentResponse
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.param.lostfound.AddCommentParam
import kr.hs.dgsw.smartschool.domain.param.lostfound.ModifyCommentParam

fun CommentResponse.toModel(): Comment = Comment(
    idx = this.idx,
    comment = this.comment,
    lostFound = this.lostFound,
    member = this.member
)

fun AddCommentParam.toRequest(): AddCommentRequest = AddCommentRequest(
    comment = this.comment,
    lostFoundId = this.lostFoundId
)

fun ModifyCommentParam.toRequest(): ModifyCommentRequest = ModifyCommentRequest(
    comment = this.comment,
    commentId = this.commentId
)
