package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.LostFoundApi
import kr.hs.dgsw.smartschool.data.network.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.data.network.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.data.network.request.lostfound.ModifyCommentRequest
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.lostpound.CommentResponse
import kr.hs.dgsw.smartschool.data.network.response.lostpound.LostFoundResponse
import kr.hs.dgsw.smartschool.data.util.Constants
import javax.inject.Inject

class LostFoundRemote @Inject constructor(
    override val api: LostFoundApi
) : BaseRemote<LostFoundApi>() {

    /*suspend fun getLostFound(page: Int, type: String): Response<List<LostFound>> {
        return api.getLostFound(Constants.INFINITE_SCROLL_LIMIT, page, type)
    }*/

    suspend fun getLostFoundAll(): Response<List<LostFoundResponse>> {
        return api.getLostFoundAll()
    }

    suspend fun getLostFoundById(id: Int): Response<LostFoundResponse> {
        return api.getLostFoundById(id)
    }
    suspend fun getLostFound(page: String, type: String): Response<List<LostFoundResponse>> =
        api.getLostFound(Constants.INFINITE_SCROLL_LIMIT, page, type)

    suspend fun getComment(lostFoundIdx: Int): Response<List<CommentResponse>> =
        api.getComment(lostFoundIdx)

    suspend fun getLostFoundSearch(search: String): Response<List<LostFoundResponse>> =
        api.getLostFoundSearch(search)

    suspend fun getMyLostFound(): Response<List<LostFoundResponse>> = api.getMyLostFound()

    suspend fun addLostFound(request: LostFoundDataRequest): Response<Any> =
        api.postLostFound(request)

    suspend fun addComment(request: AddCommentRequest): Response<Any> =
        api.postComment(request)

    suspend fun modifyLostFound(request: LostFoundDataRequest): Response<Any> =
        api.patchLostFound(request)

    suspend fun modifyComment(request: ModifyCommentRequest): Response<Any> =
        api.patchComment(request)

    suspend fun deleteLostFound(idx: Int): Response<Any> =
        api.deleteLostFound(idx)

    suspend fun deleteComment(commentIdx: Int): Response<Any> =
        api.deleteComment(commentIdx)
}
