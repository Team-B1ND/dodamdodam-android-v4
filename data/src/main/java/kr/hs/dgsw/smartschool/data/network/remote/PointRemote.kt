package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.PointApi
import kr.hs.dgsw.smartschool.domain.model.point.MyPoint
import javax.inject.Inject

class PointRemote @Inject constructor(
    override val api: PointApi
): BaseRemote<PointApi>() {

    suspend fun getMyPoint(year: String, type: Int) : MyPoint =
        api.getMyPoint(year, type).data.point

    suspend fun getMyPointTarget(target: Int) : MyPoint =
        api.getMyPointTarget(target).data.point
}