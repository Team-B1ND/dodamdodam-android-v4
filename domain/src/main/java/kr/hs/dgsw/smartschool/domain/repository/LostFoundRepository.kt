package kr.hs.dgsw.smartschool.domain.repository
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFoundComment
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPostRequest
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPutRequest
import kr.hs.dgsw.smartschool.domain.request.LostFoundRequest

interface LostFoundRepository {
    fun getLostFound(page: Int, type: Int): List<LostFound>

    fun getLostFoundSearch(search: String): List<LostFound>

    fun getLostFoundComment(lostfoundIdx: Int): List<LostFoundComment>

    fun postCreateLostFound(request: LostFoundRequest): String

    fun postLostFoundComment(request: LostFoundCommentPostRequest): String

    fun putLostFound(request: LostFoundRequest): String

    fun putLostFoundComment(request: LostFoundCommentPutRequest): String

    fun hideLostFound(lostFound: LostFound): String

    fun deleteLostFound(idx: Int): String

    fun deleteLostFoundComment(commentIdx: Int): String
}