package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.ClassInfoDataSource
import kr.hs.dgsw.smartschool.data.mapper.ClassInfoMapper
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.repository.ClassInfoRepository
import javax.inject.Inject

class ClassInfoRepositoryImpl @Inject constructor(
    private val classInfoDataSource: ClassInfoDataSource
) : ClassInfoRepository {

    private val classInfoMapper = ClassInfoMapper()

    override suspend fun getAllClassInfo(): List<Classroom> {
        return classInfoDataSource.getAllClassInfo().map { classInfoEntity -> classInfoMapper.mapToModel(classInfoEntity) }
    }

    override suspend fun getClassInfo(idx: Int): Classroom {
        return classInfoDataSource.getClassInfo(idx).let { classInfoMapper.mapToModel(it) }
    }
}
