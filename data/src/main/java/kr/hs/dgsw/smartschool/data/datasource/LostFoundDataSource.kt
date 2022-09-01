package kr.hs.dgsw.smartschool.data.datasource

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.HiddenLostFoundCache
import kr.hs.dgsw.smartschool.data.database.entity.HiddenLostFoundEntity
import kr.hs.dgsw.smartschool.data.mapper.LostFoundMapper
import kr.hs.dgsw.smartschool.data.network.remote.LostFoundRemote
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.request.*
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundRequest
import javax.inject.Inject

class LostFoundDataSource @Inject constructor(
    override val cache: HiddenLostFoundCache,
    override val remote: LostFoundRemote
) : BaseDataSource<LostFoundRemote, Any> {

    private lateinit var lostFoundList: List<LostFound>
    private lateinit var hiddenLostFoundList: List<HiddenLostFoundEntity>

    private val lostFoundMapper = LostFoundMapper()

    suspend fun getLostFound(page: Int, type: Int): List<LostFound> {
        lostFoundList =  remote.getLostFound(page,type).data.pageData
        Log.d("LostFoundDatasource",remote.getLostFound(page,type).message)
        hiddenLostFoundList = cache.getAllHiddenLostFound()
        return lostFoundList
    }

    private fun getLostFoundList(): List<LostFound> {
        val list = ArrayList<LostFound>()

        lostFoundList.forEach { lostFound ->
            var isHidden = false

            hiddenLostFoundList.forEach { hiddenLostFound ->
                if (hiddenLostFound.idx == lostFound.idx)
                    isHidden = true
            }

            if (!isHidden)
                list.add(lostFound)
        }

        return list
    }

   suspend fun getLostFoundComment(lostFoundIdx: Int): List<LostFoundComment> = remote.getLostFoundComment(lostFoundIdx).data.comments

   suspend fun postCreateLostFound(request: LostFoundRequest): String = remote.postCreateLostFound(request).message

   suspend fun postLostFoundComment(request: LostFoundDataRequest): String = remote.postLostFoundComment(request).message

   suspend fun putLostFound(request: LostFoundRequest): String = remote.putLostFound(request).message

   suspend fun putLostFoundComment(request: AddCommentRequest): String = remote.putLostFoundComment(request).message

   suspend fun deleteLostFound(idx: Int): String = remote.deleteLostFound(idx).message

   suspend fun deleteLostFoundComment(commentIdx: Int): String = remote.deleteLostFoundComment(commentIdx).message

   suspend fun getLostFoundSearch(search: String): List<LostFound> = remote.getLostFoundSearch(search).data.pageData

   suspend fun hideLostFound(lostFound: LostFound) = cache.insertHiddenLostFound(lostFoundMapper.mapToEntity(lostFound))
}
