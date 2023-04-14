package kr.hs.dgsw.smartschool.domain.repository
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound

interface LostFoundRepository {
    suspend fun getLostFound(page: String, type: String): List<LostFound>

    suspend fun getLostFoundSearch(search: String): List<LostFound>

    suspend fun getLostFoundAll(): List<LostFound>

    suspend fun getComment(lostFoundIdx: Int): List<Comment>

    suspend fun getMyLostFound(): List<LostFound>

    suspend fun addLostFound(content: String, lostFoundId: Int?, picture: String, place: String, title: String, type: String): String

    suspend fun addComment(comment: String, lostFoundId: Int): String

    suspend fun modifyLostFound(content: String, lostFoundId: Int?, picture: String, place: String, title: String, type: String): String

    suspend fun modifyComment(comment: String, commentId: Int): String

    suspend fun deleteLostFound(idx: Int): String

    suspend fun deleteComment(commentIdx: Int): String

    suspend fun getLostFound(id: Int): LostFound
}
