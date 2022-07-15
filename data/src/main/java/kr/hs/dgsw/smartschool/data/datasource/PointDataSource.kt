package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.PointRemote
import kr.hs.dgsw.smartschool.domain.model.point.MyPoint
import javax.inject.Inject

class PointDataSource @Inject constructor(
    override val remote: PointRemote,
    override val cache: Any
): BaseDataSource<PointRemote, Any>() {

    suspend fun getMyPoint(year: String, type: Int) : MyPoint = remote.getMyPoint(year, type)

    suspend fun getMyPointTarget(target: Int) : MyPoint = remote.getMyPointTarget(target)
}