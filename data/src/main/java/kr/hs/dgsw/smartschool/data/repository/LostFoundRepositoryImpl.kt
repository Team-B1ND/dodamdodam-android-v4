package kr.hs.dgsw.smartschool.data.repository
import android.util.Log
import kr.hs.dgsw.smartschool.data.datasource.LostFoundDataSource
import kr.hs.dgsw.smartschool.data.datasource.TokenDataSource
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFoundComment
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPostRequest
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPutRequest
import kr.hs.dgsw.smartschool.domain.request.LostFoundRequest
import javax.inject.Inject

class LostFoundRepositoryImpl @Inject constructor(
    private val lostFoundDataSource: LostFoundDataSource,
    private val tokenDataSource: TokenDataSource
) : LostFoundRepository {

    private lateinit var lostFoundList: List<LostFound>
    private lateinit var lostFoundCommentList: List<LostFoundComment>
    private lateinit var myId: String

    override suspend fun getLostFound(page: Int, type: Int): List<LostFound> {
        lostFoundList = lostFoundDataSource.getLostFound(page, type)
        myId = tokenDataSource.getMyId()
        getLostFoundMemberList()
        Log.d("LostFoundRepository", lostFoundList.toString())
        Log.d("LostFoundRemote", "실행")
        return lostFoundList
    }

    override suspend fun getLostFoundSearch(search: String): List<LostFound> {
        lostFoundList = lostFoundDataSource.getLostFoundSearch(search)
        myId = tokenDataSource.getMyId()
        getLostFoundMemberList()
        return lostFoundList
    }

    override suspend fun getLostFoundComment(lostFoundIdx: Int): List<LostFoundComment> {
        lostFoundCommentList = lostFoundDataSource.getLostFoundComment(lostFoundIdx)
        myId = tokenDataSource.getMyId()
        getLostFoundCommentList()
        return lostFoundCommentList
    }

    private fun getLostFoundMemberList(): List<LostFound> {
        lostFoundList.forEach { lostFound ->
            lostFound.isMine = myId == lostFound.memberId
        }
        return lostFoundList
    }

    private fun getLostFoundCommentList(): List<LostFoundComment> {
        lostFoundCommentList.forEach { lostFoundComment ->
            lostFoundComment.isMine = myId == lostFoundComment.memberId
        }
        return lostFoundCommentList
    }

    override suspend fun postCreateLostFound(request: LostFoundRequest):String {
        return lostFoundDataSource.postCreateLostFound(request)
    }

    override suspend fun postLostFoundComment(request: LostFoundCommentPostRequest): String {
        return lostFoundDataSource.postLostFoundComment(request)
    }

    override suspend fun putLostFound(request: LostFoundRequest):String {
        return lostFoundDataSource.putLostFound(request)
    }

    override suspend fun putLostFoundComment(request: LostFoundCommentPutRequest): String {
        return lostFoundDataSource.putLostFoundComment(request)
    }

    override suspend fun hideLostFound(lostFound: LostFound): String {
        lostFoundDataSource.hideLostFound(lostFound)
        return "분실물 숨김."
    }

    override suspend fun deleteLostFound(idx: Int): String {
        return lostFoundDataSource.deleteLostFound(idx)
    }

    override suspend fun deleteLostFoundComment(commentIdx: Int): String {
        return lostFoundDataSource.deleteLostFoundComment(commentIdx)
    }
}