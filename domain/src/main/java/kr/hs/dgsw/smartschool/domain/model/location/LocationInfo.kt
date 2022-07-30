package kr.hs.dgsw.smartschool.domain.model.location

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.Time

data class LocationInfo(
    val idx: Int?,
    @SerializedName("studentIdx")
    val studentIdx: Int?,
    @SerializedName("isChecked")
    val isChecked: Int?,
    @SerializedName("checkTeacherIdx")
    val checkTeacherIdx: Int?,
    val date: String?
) : Location(), Cloneable {

    constructor(time: Time, place: Place): this(null, null, null, null, null) {
        this.timeTableIdx = time.idx
        this.time = time
        this.placeIdx = place.idx
        this.place = place
    }

    constructor(timeTableIdx: Int, placeIdx: Int): this(null, null, null, null, null) {
        this.timeTableIdx = timeTableIdx
        this.time = null
        this.placeIdx = placeIdx
        this.place = null
    }

    constructor(time: Time): this(null, null, null, null, null) {
        this.timeTableIdx = time.idx
        this.time = time
        this.placeIdx = null
        this.place = null
    }

    constructor(time: Time, locationInfo: LocationInfo): this(locationInfo.idx, locationInfo.studentIdx, locationInfo.isChecked, locationInfo.checkTeacherIdx, locationInfo.date) {
        this.timeTableIdx = time.idx
        this.time = time
        this.placeIdx = locationInfo.placeIdx
        this.place = locationInfo.place
    }

    public override fun clone(): LocationInfo {
        return super.clone() as LocationInfo
    }
}