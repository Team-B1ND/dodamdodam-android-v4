package kr.hs.dgsw.smartschool.data.repository
import kr.hs.dgsw.smartschool.data.datasource.LostFoundDataSource
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.ModifyCommentRequest
import javax.inject.Inject

class LostFoundRepositoryImpl @Inject constructor(
    private val lostFoundDataSource: LostFoundDataSource
) : LostFoundRepository {

    private lateinit var lostFoundList: List<LostFound>
    private lateinit var lostFoundCommentList: List<Comment>

    override suspend fun getLostFound(page: String, type: String): List<LostFound> {
        lostFoundList = lostFoundDataSource.getLostFound(page, type)
        return lostFoundList
    }

    override suspend fun getLostFound(id: Int): LostFound {
        return lostFoundDataSource.getLostFoundById(id)
    }

    override suspend fun getLostFoundAll(): List<LostFound> {
        return lostFoundDataSource.getLostFoundAll()
    }

    override suspend fun getLostFoundSearch(search: String): List<LostFound> {
        lostFoundList = lostFoundDataSource.getLostFoundSearch(search)
        return lostFoundList
    }

    override suspend fun getComment(lostFoundIdx: Int): List<Comment> {
        lostFoundCommentList = lostFoundDataSource.getComment(lostFoundIdx)
        return lostFoundCommentList
    }

    override suspend fun getMyLostFound(): List<LostFound> {
        return lostFoundDataSource.getMyLostFound()
    }

    override suspend fun addLostFound(request: LostFoundDataRequest): String {
        return lostFoundDataSource.addLostFound(request)
    }

    override suspend fun addComment(request: AddCommentRequest): String {
        return lostFoundDataSource.addComment(request)
    }

    override suspend fun modifyLostFound(request: LostFoundDataRequest): String {
        return lostFoundDataSource.modifyLostFound(request)
    }

    override suspend fun modifyComment(request: ModifyCommentRequest): String {
        return lostFoundDataSource.modifyComment(request)
    }

    override suspend fun deleteLostFound(idx: Int): String {
        return lostFoundDataSource.deleteLostFound(idx)
    }

    override suspend fun deleteComment(commentIdx: Int): String {
        return lostFoundDataSource.deleteComment(commentIdx)
    }
}
