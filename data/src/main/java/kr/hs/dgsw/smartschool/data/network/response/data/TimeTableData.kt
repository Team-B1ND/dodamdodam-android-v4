package kr.hs.dgsw.smartschool.data.network.response.data

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.time.Time

data class TimeTableData(
    @SerializedName("timeTables")
    val timeTables: List<Time>
)
