package kr.hs.dgsw.smartschool.data.datasource

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.LostFoundRemote
import kr.hs.dgsw.smartschool.data.network.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.data.network.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.data.network.request.lostfound.ModifyCommentRequest
import kr.hs.dgsw.smartschool.data.network.response.lostpound.CommentResponse
import kr.hs.dgsw.smartschool.data.network.response.lostpound.LostFoundResponse
import javax.inject.Inject

class LostFoundDataSource @Inject constructor(
    override val cache: Any,
    override val remote: LostFoundRemote
) : BaseDataSource<LostFoundRemote, Any> {

    private lateinit var lostFoundList: List<LostFoundResponse>

    suspend fun getLostFound(page: String, type: String): List<LostFoundResponse> {
        lostFoundList = remote.getLostFound(page, type).data
        Log.d("LostFoundDataSource", lostFoundList.toString())
        return lostFoundList
    }

    suspend fun getLostFoundById(id: Int): LostFoundResponse {
        return remote.getLostFoundById(id).data
    }

    suspend fun getLostFoundAll(): List<LostFoundResponse> {
        return remote.getLostFoundAll().data
    }

    suspend fun getMyLostFound(): List<LostFoundResponse> = remote.getMyLostFound().data

    suspend fun getComment(lostFoundIdx: Int): List<CommentResponse> = remote.getComment(lostFoundIdx).data

    suspend fun addLostFound(request: LostFoundDataRequest): String = remote.addLostFound(request).message

    suspend fun addComment(request: AddCommentRequest): String = remote.addComment(request).message

    suspend fun modifyLostFound(request: LostFoundDataRequest): String = remote.modifyLostFound(request).message

    suspend fun modifyComment(request: ModifyCommentRequest): String = remote.modifyComment(request).message

    suspend fun deleteLostFound(idx: Int): String = remote.deleteLostFound(idx).message

    suspend fun deleteComment(commentIdx: Int): String = remote.deleteComment(commentIdx).message

    suspend fun getLostFoundSearch(search: String): List<LostFoundResponse> = remote.getLostFoundSearch(search).data
}
