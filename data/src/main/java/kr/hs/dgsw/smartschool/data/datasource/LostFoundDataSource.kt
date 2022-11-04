package kr.hs.dgsw.smartschool.data.datasource

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.LostFoundRemote
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.ModifyCommentRequest
import javax.inject.Inject

class LostFoundDataSource @Inject constructor(
    override val cache: Any,
    override val remote: LostFoundRemote
) : BaseDataSource<LostFoundRemote, Any> {

    private lateinit var lostFoundList: List<LostFound>

    suspend fun getLostFound(page: Int, type: String): List<LostFound> {
        lostFoundList = remote.getLostFound(page, type).data
        Log.d("LostFoundDataSource", lostFoundList.toString())
        return lostFoundList
    }

    suspend fun getLostFoundById(id: Int): LostFound {
        return remote.getLostFoundById(id).data
    }

    suspend fun getLostFoundAll():List<LostFound>{
        return remote.getLostFoundAll().data
    }

    suspend fun getMyLostFound(): List<LostFound> = remote.getMyLostFound().data

    suspend fun getComment(lostFoundIdx: Int): List<Comment> = remote.getComment(lostFoundIdx).data

    suspend fun addLostFound(request: LostFoundDataRequest): String = remote.addLostFound(request).message

    suspend fun addComment(request: AddCommentRequest): String = remote.addComment(request).message

    suspend fun modifyLostFound(request: LostFoundDataRequest): String = remote.modifyLostFound(request).message

    suspend fun modifyComment(request: ModifyCommentRequest): String = remote.modifyComment(request).message

    suspend fun deleteLostFound(idx: Int): String = remote.deleteLostFound(idx).message

    suspend fun deleteComment(commentIdx: Int): String = remote.deleteComment(commentIdx).message

    suspend fun getLostFoundSearch(search: String): List<LostFound> = remote.getLostFoundSearch(search).data
}
