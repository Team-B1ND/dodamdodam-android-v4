package kr.hs.dgsw.smartschool.domain.repository
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFoundComment
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPostRequest
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPutRequest
import kr.hs.dgsw.smartschool.domain.request.LostFoundRequest

interface LostFoundRepository {
    suspend fun getLostFound(page: Int, type: Int): List<LostFound>

    suspend fun getLostFoundSearch(search: String): List<LostFound>

    suspend fun getLostFoundComment(lostfoundIdx: Int): List<LostFoundComment>

    suspend fun postCreateLostFound(request: LostFoundRequest): String

    suspend fun postLostFoundComment(request: LostFoundCommentPostRequest): String

    suspend fun putLostFound(request: LostFoundRequest): String

    suspend fun putLostFoundComment(request: LostFoundCommentPutRequest): String

    suspend fun hideLostFound(lostFound: LostFound): String

    suspend fun deleteLostFound(idx: Int): String

    suspend fun deleteLostFoundComment(commentIdx: Int): String
}