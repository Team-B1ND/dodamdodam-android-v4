package kr.hs.dgsw.smartschool.dodamdodam.widget.extension

import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.regex.Pattern

fun String.getTime(): Date {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())

    return format.parse(this)!!
}

fun String.getDate(): Date {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", Locale.getDefault())

    return format.parse(this)!!
}

fun String.getYearDate(): Date {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    return format.parse(this)!!
}

fun String.isNotEmailValid(): Boolean {
    return !(!TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches())
}

fun String.isNotPhoneNumberValid(): Boolean {
    if (!TextUtils.isEmpty(this) && !Pattern.matches("[a-zA-Z]+", this)) {
        return this.length != 11
    }
    return true
}

fun String?.toNullValue(): String? {
    return if (this.isNullOrBlank()) null else this
}

fun String.removeBlankInString(): String =
    this.replace(" ", "")

fun String.getExtension(): String {
    val idx = this.lastIndexOf('.')
    var extension: String = ""
    if (idx > 0)
        extension = this.substring(idx)
    return extension
}
