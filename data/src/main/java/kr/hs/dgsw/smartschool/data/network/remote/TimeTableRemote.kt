package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.TimeTableApi
import kr.hs.dgsw.smartschool.data.network.response.time.TimeTableResponse

class TimeTableRemote(
    override val api: TimeTableApi
) : BaseRemote<TimeTableApi>() {

    suspend fun getAllTime(): List<TimeTableResponse> = api.getTimeTables().data
}
