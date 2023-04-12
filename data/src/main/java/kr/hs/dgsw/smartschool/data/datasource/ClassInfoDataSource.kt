package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.ClassInfoCache
import kr.hs.dgsw.smartschool.data.database.entity.ClassroomEntity
import kr.hs.dgsw.smartschool.data.mapper.toEntity
import kr.hs.dgsw.smartschool.data.network.remote.ClassInfoRemote
import kr.hs.dgsw.smartschool.data.network.response.classroom.ClassroomResponse
import javax.inject.Inject

class ClassInfoDataSource @Inject constructor(
    override val remote: ClassInfoRemote,
    override val cache: ClassInfoCache
) : BaseDataSource<ClassInfoRemote, ClassInfoCache> {

    suspend fun updateClassInfoList() =
        cache.deleteAll().also { insertAllClassInfoRemote() }

    suspend fun getAllClassInfo(): List<ClassroomEntity> =
        cache.getAllClassInfo().ifEmpty { getAllClassInfoRemote() }

    suspend fun getClassInfo(idx: Int): ClassroomEntity =
        cache.getClassInfo(idx).let { if (it.grade == -1) getClassInfoRemote(idx) else it }

    private suspend fun insertAllClassInfoRemote() =
        remote.getClassInfo().also { insertClassInfoList(it) }

    private suspend fun getAllClassInfoRemote(): List<ClassroomEntity> =
        remote.getClassInfo()
            .let { classInfoList -> classInfoList.map { classInfo -> classInfo.toEntity() } }
            .also { classInfoEntityList -> cache.insertClassInfoList(classInfoEntityList) }

    private suspend fun getClassInfoRemote(idx: Int): ClassroomEntity =
        getAllClassInfoRemote().let { it.filter { classInfoEntity -> classInfoEntity.id == idx }[0] }

    private suspend fun insertClassInfoList(classroomList: List<ClassroomResponse>) =
        cache.insertClassInfoList(classroomList.map { classInfo -> classInfo.toEntity() })
}
