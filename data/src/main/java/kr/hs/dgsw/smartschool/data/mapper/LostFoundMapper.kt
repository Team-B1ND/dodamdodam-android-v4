package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.HiddenLostFoundEntity
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

class LostFoundMapper : BaseEntityMapper<LostFound, HiddenLostFoundEntity> {
    override fun mapToEntity(model: LostFound): HiddenLostFoundEntity {
        return HiddenLostFoundEntity(
            model.idx,
            model.member.id,
            model.title,
            model.place,
            model.content,
            null
        )
    }

    override fun mapToModel(entity: HiddenLostFoundEntity): LostFound {
        TODO()
    }
}
