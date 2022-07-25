package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.ClassInfoEntity
import kr.hs.dgsw.smartschool.domain.model.classinfo.ClassInfo

class ClassInfoMapper : BaseEntityMapper<ClassInfo, ClassInfoEntity> {
    override fun mapToModel(entity: ClassInfoEntity): ClassInfo {
        return ClassInfo(
            entity.idx,
            entity.grade,
            entity.placeIdx,
            entity.room
        )
    }

    override fun mapToEntity(model: ClassInfo): ClassInfoEntity {
        return ClassInfoEntity(
            model.idx,
            model.grade,
            model.room,
            model.placeIdx
        )
    }
}