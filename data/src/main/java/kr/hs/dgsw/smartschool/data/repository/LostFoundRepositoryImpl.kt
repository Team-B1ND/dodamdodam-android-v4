package kr.hs.dgsw.smartschool.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.b1nd.dodamdodam.data.datasource.LostFoundDataSource
import kr.hs.dgsw.b1nd.dodamdodam.data.datasource.StudentDataSource
import kr.hs.dgsw.b1nd.dodamdodam.data.datasource.TokenDataSource
import kr.hs.dgsw.b1nd.dodamdodam.data.mapper.MemberMapper
import kr.hs.dgsw.b1nd.dodamdodam.data.mapper.StudentMapper
import kr.hs.dgsw.b1nd.dodamdodam.domain.model.lostfound.LostFound
import kr.hs.dgsw.b1nd.dodamdodam.domain.model.lostfound.LostFoundComment
import kr.hs.dgsw.b1nd.dodamdodam.domain.model.member.Member
import kr.hs.dgsw.b1nd.dodamdodam.domain.model.member.Student
import kr.hs.dgsw.b1nd.dodamdodam.domain.repository.LostFoundRepository
import kr.hs.dgsw.b1nd.dodamdodam.domain.request.LostFoundCommentPostRequest
import kr.hs.dgsw.b1nd.dodamdodam.domain.request.LostFoundCommentPutRequest
import kr.hs.dgsw.b1nd.dodamdodam.domain.request.LostFoundRequest
import kr.hs.dgsw.smartschool.data.datasource.LostFoundDataSource
import kr.hs.dgsw.smartschool.data.datasource.TokenDataSource
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFoundComment
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPostRequest
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPutRequest
import kr.hs.dgsw.smartschool.domain.request.LostFoundRequest
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LostFoundRepositoryImpl @Inject constructor(
    private val lostFoundDataSource: LostFoundDataSource,
    private val tokenDataSource: TokenDataSource
) : LostFoundRepository {

    private lateinit var lostFoundList: List<LostFound>
    private lateinit var lostFoundCommentList: List<LostFoundComment>
    private lateinit var myId: String

    override fun getLostFound(page: Int, type: Int): List<LostFound> {
        return lostFoundDataSource.getLostFound(page, type)
            .flatMap { lostFoundList -> this.lostFoundList = lostFoundList
                tokenDataSource.getMyId()
                    .flatMap { myId ->
                        this.myId = myId
                        Single.just(getLostFoundMemberList())
                    }
            }.delay(500, TimeUnit.MILLISECONDS)
    }

    override fun getLostFoundSearch(search: String): List<LostFound> {
        return lostFoundDataSource.getLostFoundSearch(search)
            .flatMap { lostFoundList -> this.lostFoundList = lostFoundList
                tokenDataSource.getMyId()
                    .flatMap { myId ->
                        this.myId = myId
                        Single.just(getLostFoundMemberList())
                    }
            }.delay(500, TimeUnit.MILLISECONDS)
    }

    override fun getLostFoundComment(lostfoundIdx: Int): List<LostFoundComment> {
        return lostFoundDataSource.getLostFoundComment(lostfoundIdx)
            .flatMap { lostFoundCommentList ->
                this.lostFoundCommentList = lostFoundCommentList
                tokenDataSource.getMyId()
                    .flatMap { myId ->
                        this.myId = myId
                        Single.just(getLostFoundCommentList())
                    }
            }.delay(500, TimeUnit.MILLISECONDS)
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

    override fun postCreateLostFound(request: LostFoundRequest):String {
        return lostFoundDataSource.postCreateLostFound(request).ignoreElement()
    }

    override fun postLostFoundComment(request: LostFoundCommentPostRequest): Completable {
        return lostFoundDataSource.postLostFoundComment(request).
    }

    override fun putLostFound(request: LostFoundRequest):String {
        return lostFoundDataSource.putLostFound(request).ignoreElement()
    }

    override fun putLostFoundComment(request: LostFoundCommentPutRequest): Completable {
        return lostFoundDataSource.putLostFoundComment(request).ignoreElement()
    }

    override fun hideLostFound(lostFound: LostFound): Completable {
        return lostFoundDataSource.hideLostFound(lostFound)
    }

    override fun deleteLostFound(idx: Int): Completable {
        return lostFoundDataSource.deleteLostFound(idx).ignoreElement()
    }

    override fun deleteLostFoundComment(commentIdx: Int): Completable {
        return lostFoundDataSource.deleteLostFoundComment(commentIdx).ignoreElement()
    }
}