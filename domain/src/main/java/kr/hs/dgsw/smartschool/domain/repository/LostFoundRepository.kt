package kr.hs.dgsw.smartschool.domain.repository
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.param.lostfound.AddCommentParam
import kr.hs.dgsw.smartschool.domain.param.lostfound.LostFoundDataParam
import kr.hs.dgsw.smartschool.domain.param.lostfound.ModifyCommentParam

interface LostFoundRepository {
    suspend fun getLostFound(page: Int, type: String): List<LostFound>

    suspend fun getLostFoundSearch(search: String): List<LostFound>

    suspend fun getLostFoundAll(): List<LostFound>

    suspend fun getComment(lostFoundIdx: Int): List<Comment>

    suspend fun getMyLostFound(): List<LostFound>

    suspend fun addLostFound(request: LostFoundDataParam): String

    suspend fun addComment(request: AddCommentParam): String

    suspend fun modifyLostFound(request: LostFoundDataParam): String

    suspend fun modifyComment(request: ModifyCommentParam): String

    suspend fun deleteLostFound(idx: Int): String

    suspend fun deleteComment(commentIdx: Int): String

    suspend fun getLostFound(id: Int): LostFound
}
