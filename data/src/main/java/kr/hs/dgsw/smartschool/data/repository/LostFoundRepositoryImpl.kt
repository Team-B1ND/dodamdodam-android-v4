package kr.hs.dgsw.smartschool.data.repository
import android.util.Log
import kr.hs.dgsw.smartschool.data.datasource.LostFoundDataSource
import kr.hs.dgsw.smartschool.data.datasource.TokenDataSource
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.ModifyCommentRequest
import javax.inject.Inject

class LostFoundRepositoryImpl @Inject constructor(
    private val lostFoundDataSource: LostFoundDataSource,
    private val tokenDataSource: TokenDataSource
) : LostFoundRepository {

    private lateinit var lostFoundList: List<LostFound>
    private lateinit var lostFoundCommentList: List<Comment>
    private lateinit var myId: String

    override suspend fun getLostFound(page: Int, type: String): List<LostFound> {
        lostFoundList = lostFoundDataSource.getLostFound(page, type)
        myId = tokenDataSource.getMyId()
        Log.d("LostFoundRepository", lostFoundList.toString())
        return lostFoundList
    }

    override suspend fun getLostFound(id: Int): LostFound {
        return lostFoundDataSource.getLostFoundById(id)
    }

    override suspend fun getLostFoundSearch(search: String): List<LostFound> {
        lostFoundList = lostFoundDataSource.getLostFoundSearch(search)
        myId = tokenDataSource.getMyId()
        return lostFoundList
    }

    override suspend fun getComment(lostFoundIdx: Int): List<Comment> {
        lostFoundCommentList = lostFoundDataSource.getComment(lostFoundIdx)
        myId = tokenDataSource.getMyId()
        return lostFoundCommentList
    }

    override suspend fun getMyLostFound(): List<LostFound> {
        return lostFoundDataSource.getMyLostFound()
    }

    override suspend fun addLostFound(request: LostFoundDataRequest):String {
        return lostFoundDataSource.addLostFound(request)
    }

    override suspend fun addComment(request: AddCommentRequest): String {
        return lostFoundDataSource.addComment(request)
    }

    override suspend fun modifyLostFound(request: LostFoundDataRequest):String {
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