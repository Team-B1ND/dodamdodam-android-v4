package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.PointApi
import kr.hs.dgsw.smartschool.domain.model.point.Point
import javax.inject.Inject

class PointRemote @Inject constructor(
    override val api: PointApi
) : BaseRemote<PointApi>() {

    suspend fun getMyYearPoints(year: Int): List<Point> =
        api.getMyYearPoints(year).data
}
