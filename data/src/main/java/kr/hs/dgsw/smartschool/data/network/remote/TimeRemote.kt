package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.TimeApi
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable

class TimeRemote(override val api: TimeApi) : BaseRemote<TimeApi>() {
    suspend fun getAllTime(): List<TimeTable> = api.getTimeTable().data.timeTables
}
