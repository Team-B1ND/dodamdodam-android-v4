package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.TimeTableApi
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable

class TimeTableRemote(
    override val api: TimeTableApi
) : BaseRemote<TimeTableApi>() {

    suspend fun getAllTime(): List<TimeTable> = api.getTimeTables().data.timeTables

}
