package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.ClassInfoCache
import kr.hs.dgsw.smartschool.data.database.entity.ClassroomEntity
import kr.hs.dgsw.smartschool.data.mapper.ClassInfoMapper
import kr.hs.dgsw.smartschool.data.network.remote.ClassInfoRemote
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import javax.inject.Inject

class ClassInfoDataSource @Inject constructor(
    override val remote: ClassInfoRemote,
    override val cache: ClassInfoCache
) : BaseDataSource<ClassInfoRemote, ClassInfoCache> {

    private val classInfoMapper = ClassInfoMapper()

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
            .let { classInfoList -> classInfoList.map { classInfo -> classInfoMapper.mapToEntity(classInfo) } }
            .also { classInfoEntityList -> cache.insertClassInfoList(classInfoEntityList) }

    private suspend fun getClassInfoRemote(idx: Int): ClassroomEntity =
        getAllClassInfoRemote().let { it.filter { classInfoEntity -> classInfoEntity.id == idx }[0] }

    private suspend fun insertClassInfoList(classroomList: List<Classroom>) =
        cache.insertClassInfoList(classroomList.map { classInfo -> classInfoMapper.mapToEntity(classInfo) })
}
