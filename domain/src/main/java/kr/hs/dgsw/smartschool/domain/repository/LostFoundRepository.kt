package kr.hs.dgsw.smartschool.domain.repository
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundRequest

interface LostFoundRepository {
    suspend fun getLostFound(page: Int, type: Int): List<LostFound>

    suspend fun getLostFoundSearch(search: String): List<LostFound>

    suspend fun getLostFoundComment(lostfoundIdx: Int): List<LostFoundComment>

    suspend fun postCreateLostFound(request: LostFoundRequest): String

    suspend fun postLostFoundComment(request: LostFoundDataRequest): String

    suspend fun putLostFound(request: LostFoundRequest): String

    suspend fun putLostFoundComment(request: AddCommentRequest): String

    suspend fun hideLostFound(lostFound: LostFound): String

    suspend fun deleteLostFound(idx: Int): String

    suspend fun deleteLostFoundComment(commentIdx: Int): String
}