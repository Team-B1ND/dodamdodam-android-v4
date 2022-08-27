package kr.hs.dgsw.smartschool.domain.model.time

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Date

class TimeTable(
    val id: Int,
    val name: String,
    val type: WeekType,
    @field:SerializedName("startTime") val startTime: String,
    @field:SerializedName("endTime") val endTime: String
) {
    constructor() : this(0, "", WeekType.WEEKDAY, "", "")

    @SuppressLint("SimpleDateFormat")
    private val format = SimpleDateFormat("HH:mm")

    private val now = Date()

    private val formatNow = format.parse(format.format(now))!!

    override fun toString(): String {
        return name
    }

    fun isNow(): Boolean {
        return formatNow.after(format.parse(startTime)) && formatNow.before(format.parse(endTime))
    }

    fun isBefore(): Boolean {
        return formatNow.before(format.parse(startTime))
    }

    fun isAfter(): Boolean {
        return formatNow.after(format.parse(endTime))
    }
}
