package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.ClassInfoDataSource
import kr.hs.dgsw.smartschool.data.mapper.ClassInfoMapper
import kr.hs.dgsw.smartschool.domain.model.classinfo.ClassInfo
import kr.hs.dgsw.smartschool.domain.repository.ClassInfoRepository
import javax.inject.Inject

class ClassInfoRepositoryImpl @Inject constructor(
    private val classInfoDataSource: ClassInfoDataSource
) : ClassInfoRepository {

    private val classInfoMapper = ClassInfoMapper()

    override suspend fun getAllClassInfo(): List<ClassInfo> {
        return classInfoDataSource.getAllClassInfo().map { classInfoEntity -> classInfoMapper.mapToModel(classInfoEntity) }
    }

    override suspend fun getClassInfo(idx: Int): ClassInfo {
        return classInfoDataSource.getClassInfo(idx).let { classInfoMapper.mapToModel(it) }
    }
}