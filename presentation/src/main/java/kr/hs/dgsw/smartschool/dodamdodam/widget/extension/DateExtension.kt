package kr.hs.dgsw.smartschool.dodamdodam.widget.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.monthDateTimeWeekFormat(): String {
    val format = SimpleDateFormat("MM-dd HH:mm (E)", Locale.getDefault())
    return format.format(this)
}

fun Date.yearDateTimeWeekFormat(): String {
    val format = SimpleDateFormat("yyyy-MM-dd H:mm (E)", Locale.getDefault())
    return format.format(this)
}

fun Date.yearDateTimeFormat(): String {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return format.format(this)
}

fun Date.yearDateWeekFormat(): String {
    val format = SimpleDateFormat("yyyy-MM-dd (E)", Locale.getDefault())
    return format.format(this)
}

fun Date.yearDateFormat(): String {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.format(this)
}

fun Date.monthDateFormatToKR(): String {
    val format = SimpleDateFormat("MM-dd", Locale.getDefault())

    return format.format(this)
}

fun Date.yearFormat(): String {
    val format = SimpleDateFormat("yyyy", Locale.getDefault())
    return format.format(this)
}

fun Date.monthFormat(): String {
    val format = SimpleDateFormat("MM", Locale.getDefault())
    return format.format(this)
}

fun Date.dateFormat(): String {
    val format = SimpleDateFormat("dd", Locale.getDefault())
    return format.format(this)
}

fun Date.timeFormat(): String {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(this)
}

fun Date.hourFormat(): String {
    val format = SimpleDateFormat("HH", Locale.getDefault())
    return format.format(this)
}

fun Date.minuteFormat(): String {
    val format = SimpleDateFormat("mm", Locale.getDefault())
    return format.format(this)
}

fun Date.getFormatDate(): Date {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.parse(format.format(this))!!
}

fun Date.getFormatTime(): Date {
    val format = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return format.parse(format.format(this))!!
}

fun Date.isPassTime(): Boolean {
    return this.before(Date())
}