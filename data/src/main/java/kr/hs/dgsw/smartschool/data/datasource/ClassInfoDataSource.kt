package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.ClassInfoCache
import kr.hs.dgsw.smartschool.data.database.entity.ClassInfoEntity
import kr.hs.dgsw.smartschool.data.mapper.ClassInfoMapper
import kr.hs.dgsw.smartschool.data.network.remote.ClassInfoRemote
import kr.hs.dgsw.smartschool.domain.model.classinfo.ClassInfo
import javax.inject.Inject

class ClassInfoDataSource @Inject constructor(
    override val remote: ClassInfoRemote,
    override val cache: ClassInfoCache
) : BaseDataSource<ClassInfoRemote, ClassInfoCache>() {

    private val classInfoMapper = ClassInfoMapper()

    suspend fun updateClassInfoList() =
        cache.deleteAll().also { insertAllClassInfoRemote() }

    suspend fun getAllClassInfo(): List<ClassInfoEntity> =
        cache.getAllClassInfo().ifEmpty { getAllClassInfoRemote() }

    suspend fun getClassInfo(idx: Int): ClassInfoEntity =
        cache.getClassInfo(idx).let { if (it.grade == -1) getClassInfoRemote(idx) else it }

    private suspend fun insertAllClassInfoRemote() =
        remote.getClassInfo().also { insertClassInfoList(it) }

    private suspend fun getAllClassInfoRemote(): List<ClassInfoEntity> =
        remote.getClassInfo()
            .let { classInfoList -> classInfoList.map { classInfo -> classInfoMapper.mapToEntity(classInfo) } }
            .also { classInfoEntityList -> cache.insertClassInfoList(classInfoEntityList) }

    private suspend fun getClassInfoRemote(idx: Int): ClassInfoEntity =
        getAllClassInfoRemote().let { it.filter { classInfoEntity -> classInfoEntity.idx == idx }[0] }

    private suspend fun insertClassInfoList(classInfoList: List<ClassInfo>) =
        cache.insertClassInfoList(classInfoList.map { classInfo -> classInfoMapper.mapToEntity(classInfo) })
}
