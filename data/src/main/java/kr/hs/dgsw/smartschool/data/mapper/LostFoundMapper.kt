package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.ClassInfoEntity
import kr.hs.dgsw.smartschool.data.database.entity.HiddenLostFoundEntity
import kr.hs.dgsw.smartschool.domain.model.classinfo.ClassInfo
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

class LostFoundMapper : BaseEntityMapper<LostFound, HiddenLostFoundEntity> {
    override fun mapToModel(entity: HiddenLostFoundEntity): LostFound {
        return LostFound(
            entity.idx,
            entity.memberId,
            null,
            entity.title,
            null,
            null,
            entity.place,
            entity.content,
            null,


            entity.placeIdx,
            entity.room
        )
    }

    override fun mapToEntity(model: LostFound): HiddenLostFoundEntity {
        return HiddenLostFoundEntity(
            model.idx,
            model.grade,
            model.room,
            model.placeIdx
        )
    }
}
