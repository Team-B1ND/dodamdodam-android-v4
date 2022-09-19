package kr.hs.dgsw.smartschool.domain.repository
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.ModifyCommentRequest

interface LostFoundRepository {
    suspend fun getLostFound(page: Int, type: String): List<LostFound>

    suspend fun getLostFoundSearch(search: String): List<LostFound>

    suspend fun getComment(lostfoundIdx: Int): List<Comment>

    suspend fun getMyLostFound() : List<LostFound>

    suspend fun addLostFound(request: LostFoundDataRequest): String

    suspend fun addComment(request: AddCommentRequest): String

    suspend fun modifyLostFound(request: LostFoundDataRequest): String

    suspend fun modifyComment(request:ModifyCommentRequest): String

    suspend fun deleteLostFound(idx: Int): String

    suspend fun deleteComment(commentIdx: Int): String

    suspend fun getLostFound(id:Int):LostFound
}