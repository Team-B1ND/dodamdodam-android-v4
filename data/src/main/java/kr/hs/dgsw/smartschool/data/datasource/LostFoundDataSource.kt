package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.HiddenLostFoundCache
import kr.hs.dgsw.smartschool.data.database.entity.HiddenLostFoundEntity
import kr.hs.dgsw.smartschool.data.mapper.LostFoundMapper
import kr.hs.dgsw.smartschool.data.network.remote.LostFoundRemote
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.ModifyCommentRequest
import javax.inject.Inject

class LostFoundDataSource @Inject constructor(
    override val cache: HiddenLostFoundCache,
    override val remote: LostFoundRemote
) : BaseDataSource<LostFoundRemote, Any> {

    private lateinit var lostFoundList: List<LostFound>
    private lateinit var hiddenLostFoundList: List<HiddenLostFoundEntity>

    private val lostFoundMapper = LostFoundMapper()

    suspend fun getLostFound(page: Int, type:String): List<LostFound> {
        lostFoundList = remote.getLostFound(page,type).data
        hiddenLostFoundList = cache.getAllHiddenLostFound()
        return getLostFoundList()
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

    suspend fun getMyLostFound() : List<LostFound> = remote.getMyLostFound().data

   suspend fun getComment(lostFoundIdx: Int): List<Comment> = remote.getComment(lostFoundIdx).data

   suspend fun addLostFound(request: LostFoundDataRequest): String = remote.addLostFound(request).message

   suspend fun addComment(request: AddCommentRequest): String = remote.addComment(request).message

   suspend fun modifyLostFound(request: LostFoundDataRequest): String = remote.modifyLostFound(request).message

   suspend fun modifyComment(request: ModifyCommentRequest): String = remote.modifyComment(request).message

   suspend fun deleteLostFound(idx: Int): String = remote.deleteLostFound(idx).message

   suspend fun deleteComment(commentIdx: Int): String = remote.deleteComment(commentIdx).message

   suspend fun getLostFoundSearch(search: String): List<LostFound> = remote.getLostFoundSearch(search).data

   suspend fun hideLostFound(lostFound: LostFound) = cache.insertHiddenLostFound(lostFoundMapper.mapToEntity(lostFound))
}
