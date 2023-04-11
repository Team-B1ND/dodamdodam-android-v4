package kr.hs.dgsw.smartschool.data.repository
import kr.hs.dgsw.smartschool.data.datasource.LostFoundDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.network.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.data.network.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.data.network.request.lostfound.ModifyCommentRequest
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import javax.inject.Inject

class LostFoundRepositoryImpl @Inject constructor(
    private val lostFoundDataSource: LostFoundDataSource
) : LostFoundRepository {

    private lateinit var lostFoundList: List<LostFound>
    private lateinit var lostFoundCommentList: List<Comment>

    override suspend fun getLostFound(page: String, type: String): List<LostFound> {
        lostFoundList = lostFoundDataSource.getLostFound(page, type).map { lostFoundResponse -> lostFoundResponse.toModel() }
        return lostFoundList
    }

    override suspend fun getLostFound(id: Int): LostFound {
        return lostFoundDataSource.getLostFoundById(id).toModel()
    }

    override suspend fun getLostFoundAll(): List<LostFound> {
        return lostFoundDataSource.getLostFoundAll().map { lostFoundResponse -> lostFoundResponse.toModel() }
    }

    override suspend fun getLostFoundSearch(search: String): List<LostFound> {
        lostFoundList = lostFoundDataSource.getLostFoundSearch(search).map { lostFoundResponse -> lostFoundResponse.toModel() }
        return lostFoundList
    }

    override suspend fun getComment(lostFoundIdx: Int): List<Comment> {
        lostFoundCommentList = lostFoundDataSource.getComment(lostFoundIdx).map { commentResponse -> commentResponse.toModel() }
        return lostFoundCommentList
    }

    override suspend fun getMyLostFound(): List<LostFound> {
        return lostFoundDataSource.getMyLostFound().map { lostFoundResponse -> lostFoundResponse.toModel() }
    }

    override suspend fun addLostFound(content: String, lostFoundId: Int?, picture: String, place: String, title: String, type: String): String {
        return lostFoundDataSource.addLostFound(LostFoundDataRequest(content, lostFoundId, picture, place, title, type))
    }

    override suspend fun addComment(comment: String, lostFoundId: Int): String {
        return lostFoundDataSource.addComment(AddCommentRequest(comment, lostFoundId))
    }

    override suspend fun modifyLostFound(content: String, lostFoundId: Int?, picture: String, place: String, title: String, type: String): String {
        return lostFoundDataSource.modifyLostFound(LostFoundDataRequest(content, lostFoundId, picture, place, title, type))
    }

    override suspend fun modifyComment(comment: String, commentId: Int): String {
        return lostFoundDataSource.modifyComment(ModifyCommentRequest(comment, commentId))
    }

    override suspend fun deleteLostFound(idx: Int): String {
        return lostFoundDataSource.deleteLostFound(idx)
    }

    override suspend fun deleteComment(commentIdx: Int): String {
        return lostFoundDataSource.deleteComment(commentIdx)
    }
}
