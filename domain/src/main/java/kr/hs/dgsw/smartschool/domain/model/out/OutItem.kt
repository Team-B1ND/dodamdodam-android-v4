package kr.hs.dgsw.smartschool.domain.model.out

import kr.hs.dgsw.smartschool.domain.model.member.StudentId
import kr.hs.dgsw.smartschool.domain.model.member.TeacherId
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

open class OutItem(
    val arrivedDate: Date,
    val checkedDate: Date,
    val endOutDate: Date,
    val id: Int,
    val reason: String,
    val startOutDate: Date,
    val status: OutStatus,
    val student: StudentId,
    val teacher: TeacherId
) : Serializable {

    constructor() : this(Date(), Date(), Date(), 0, "", Date(), OutStatus.PENDING, StudentId(-1), TeacherId(-1))

    val startDate: String
        get() {
            return startOutDate.yearDateFormat()
        }
    val startTime: String
        get() {
            return startOutDate.timeFormat()
        }

    val endDate: String
        get() {
            return endOutDate.yearDateFormat()
        }

    val endTime: String
        get() {
            return endOutDate.timeFormat()
        }


    override fun toString(): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return "${format.format(startOutDate)} ~\n ${format.format(endOutDate)}"
    }

    fun isNow(): Boolean {
        val now = Date()
        return now.after(startOutDate) && now.before(endOutDate)
    }

    private fun Date.yearDateFormat(): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.format(this)
    }

    private fun Date.timeFormat(): String {
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        return format.format(this)
    }


    fun isOutSleeping(): Boolean {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.format(startOutDate) != format.format(endOutDate)
    }

    fun isPassTime(): Boolean {
        val now = Date()
        return now.after(endOutDate)
    }
}
