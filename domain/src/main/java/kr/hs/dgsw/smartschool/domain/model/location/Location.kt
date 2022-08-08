package kr.hs.dgsw.smartschool.domain.model.location

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.Time


open class Location (
    var time: Time?,
    var place: Place?,
    @SerializedName("timeTableIdx")
    var timeTableIdx: Int?,
    @SerializedName("placeIdx")
    var placeIdx: Int?
) {
    constructor(): this(null, null, null, null)

    constructor(timeTableIdx: Int, placeIdx: Int): this(null, null, timeTableIdx, placeIdx)
}