package kr.hs.dgsw.smartschool.data.network.response.out

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import kr.hs.dgsw.smartschool.data.network.response.member.StudentIdResponse
import kr.hs.dgsw.smartschool.data.network.response.member.TeacherIdResponse
import kr.hs.dgsw.smartschool.data.util.parceler.StudentIdParceler
import kr.hs.dgsw.smartschool.data.util.parceler.TeacherIdParceler
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Parcelize
open class OutItemResponse(
    val arrivedDate: Date,
    val checkedDate: Date,
    val endOutDate: Date,
    val id: Int,
    val reason: String,
    val startOutDate: Date,
    val status: OutStatusResponse,
    @TypeParceler<StudentIdResponse, StudentIdParceler> val student: StudentIdResponse,
    @TypeParceler<TeacherIdResponse, TeacherIdParceler> val teacher: TeacherIdResponse
) : Serializable, Parcelable {

    val startDate: String
        get() {
            return startOutDate.yearDateFormat()
        }

    val startTime: String
        get() {
            return startOutDate.timeFormat()
        }

    val startTimeHour: String
        get() {
            return startTime.split(':')[0]
        }

    val startTimeMinute: String
        get() {
            return startTime.split(':')[1]
        }

    val endDate: String
        get() {
            return endOutDate.yearDateFormat()
        }

    val endTime: String
        get() {
            return endOutDate.timeFormat()
        }

    val endTimeHour: String
        get() {
            return endTime.split(':')[0]
        }

    val endTimeMinute: String
        get() {
            return endTime.split(':')[1]
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
