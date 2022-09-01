package kr.hs.dgsw.smartschool.data.network.remote

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.LostFoundApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.util.Constants
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFoundData
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundRequest
import javax.inject.Inject

class LostFoundRemote @Inject constructor(
    override val api: LostFoundApi
    ) : BaseRemote<LostFoundApi>() {

    fun getLostFound(page: Int, type: Int): Response<LostFoundData> {
        Log.d("LostFoundRemote", "실행")
        return api.getLostFound(Constants.INFINITE_SCROLL_LIMIT, page, type)
    }

    fun getLostFoundComment(lostFoundIdx: Int): Response<LostFoundData>  =
        api.getLostFoundComment(lostFoundIdx)

    fun postCreateLostFound(request: LostFoundRequest):Response<Any> =
        api.postCreateLostFound(request)

    fun postLostFoundComment(request: LostFoundDataRequest):Response<Any> =
        api.postLostFoundComment(request)

    fun putLostFound(request: LostFoundRequest):Response<Any> =
        api.putLostFound(request)

    fun putLostFoundComment(request: AddCommentRequest):Response<Any> =
        api.putLostFoundComment(request)

    fun deleteLostFound(idx: Int):Response<Any> =
        api.deleteLostFound(idx)

    fun deleteLostFoundComment(commentIdx: Int):Response<Any> =
        api.deleteLostFoundComment(commentIdx)

    fun getLostFoundSearch(search: String): Response<LostFoundData> =
        api.getLostFoundSearch(search)
}