package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.PointRemote
import kr.hs.dgsw.smartschool.domain.model.point.Point
import javax.inject.Inject

class PointDataSource @Inject constructor(
    override val remote: PointRemote,
    override val cache: Any
) : BaseDataSource<PointRemote, Any> {

    suspend fun getMyYearPoints(year: Int): List<Point> = remote.getMyYearPoints(year)
}
