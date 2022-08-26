package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.BusApi
import kr.hs.dgsw.smartschool.data.network.api.LostFoundApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.util.Constants
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFoundComment
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFoundData
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPostRequest
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPutRequest
import kr.hs.dgsw.smartschool.domain.request.LostFoundRequest
import javax.inject.Inject

class LostFoundRemote @Inject constructor(
    override val api: LostFoundApi)
    : BaseRemote<LostFoundApi>() {

    fun getLostFound(page: Int, type: Int): Response<LostFoundData> =
        api.getLostFound(Constants.INFINITE_SCROLL_LIMIT, page, type)

    fun getLostFoundComment(lostfoundIdx: Int): Response<LostFoundData>  =
        api.getLostFoundComment(lostfoundIdx)

    fun postCreateLostFound(request: LostFoundRequest):Response<Any> =
        api.postCreateLostFound(request)

    fun postLostFoundComment(request: LostFoundCommentPostRequest):Response<Any> =
        api.postLostFoundComment(request)

    fun putLostFound(request: LostFoundRequest):Response<Any> =
        api.putLostFound(request)

    fun putLostFoundComment(request: LostFoundCommentPutRequest):Response<Any> =
        api.putLostFoundComment(request)

    fun deleteLostFound(idx: Int):Response<Any> =
        api.deleteLostFound(idx)

    fun deleteLostFoundComment(commentIdx: Int):Response<Any> =
        api.deleteLostFoundComment(commentIdx)

    fun getLostFoundSearch(search: String): Response<Any> =
        api.getLostFoundSearch(search)
}