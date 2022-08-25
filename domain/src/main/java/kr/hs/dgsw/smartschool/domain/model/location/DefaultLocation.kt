package kr.hs.dgsw.smartschool.domain.model.location

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.Time

data class DefaultLocation(
    val day: Int,
    @SerializedName("studentIdx")
    val studentIdx: Int,
    @SerializedName("timeTableIdx")
    val timeTableIdx: Int,
    @SerializedName("placeIdx")
    val placeIdx: Int,

    var time: Time?,
    var place: Place?
) {
    constructor(time: Time) : this(0, 0, time.idx, 0, time, null)

    constructor(time: Time, defaultLocation: DefaultLocation) : this(
        defaultLocation.day,
        defaultLocation.studentIdx,
        defaultLocation.timeTableIdx,
        defaultLocation.placeIdx,
        time,
        defaultLocation.place
    )
}
