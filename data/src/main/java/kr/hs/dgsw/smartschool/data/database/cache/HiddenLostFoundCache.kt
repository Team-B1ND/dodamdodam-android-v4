package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.entity.HiddenLostFoundEntity
import javax.inject.Inject

class HiddenLostFoundCache @Inject constructor(application: Application) : BaseCache(application) {

    private val hiddenLostFoundDao = database.hiddenLostFoundDao()

    suspend fun insertHiddenLostFound(hiddenLostFound: HiddenLostFoundEntity) = hiddenLostFoundDao.insertHiddenLostFound(hiddenLostFound.idx, hiddenLostFound.memberId, hiddenLostFound.title, hiddenLostFound.place ?: "", hiddenLostFound.content, hiddenLostFound.contact ?: "")

    suspend fun deleteAll() = hiddenLostFoundDao.deleteHiddenLostFound()

    suspend fun getAllHiddenLostFound(): List<HiddenLostFoundEntity> = hiddenLostFoundDao.getHiddenLostFoundList()
}
