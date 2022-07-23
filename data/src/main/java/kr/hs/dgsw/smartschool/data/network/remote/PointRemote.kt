package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.PointApi
import kr.hs.dgsw.smartschool.domain.model.point.MyTargetPoint
import kr.hs.dgsw.smartschool.domain.model.point.MyYearPoint
import javax.inject.Inject

class PointRemote @Inject constructor(
    override val api: PointApi
): BaseRemote<PointApi>() {

    suspend fun getMyPoint(year: String, type: Int) : MyYearPoint =
        api.getMyPoint(year, type).data.point

    suspend fun getMyPointTarget(target: Int) : MyTargetPoint =
        api.getMyPointTarget(target).data.point
}