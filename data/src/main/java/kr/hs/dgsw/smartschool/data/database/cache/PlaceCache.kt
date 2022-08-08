package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.dao.PlaceDao
import kr.hs.dgsw.smartschool.data.database.entity.PlaceEntity
import javax.inject.Inject

class PlaceCache @Inject constructor(application: Application): BaseCache(application) {

    private val placeDao: PlaceDao = database.placeDao()

    suspend fun insertPlace(placeEntityList: List<PlaceEntity>) = placeDao.insert(placeEntityList)

    suspend fun deleteAll() = placeDao.deleteAll()

    suspend fun getAllPlace() : List<PlaceEntity> = placeDao.getAllPlace()
}