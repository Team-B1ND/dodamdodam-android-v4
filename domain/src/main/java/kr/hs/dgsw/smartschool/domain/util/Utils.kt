package kr.hs.dgsw.smartschool.domain.util

import kr.hs.dgsw.smartschool.domain.model.time.WeekType
import org.json.JSONObject
import retrofit2.HttpException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

object Utils {

    const val TOKEN_EXCEPTION = "token_exception"
    const val EXCEPTION = "오류가 발생했습니다."

    fun getWeekType(): WeekType {
        if (isWeekend()) {
            return WeekType.WEEKEND
        }
        return WeekType.WEEKDAY
    }

    fun getWeekType(dayOfWeek: Int): WeekType {
        if (dayOfWeek == 0 || dayOfWeek == 6 || dayOfWeek == 8) {
            return WeekType.WEEKEND
        }
        return WeekType.WEEKDAY
    }

    private fun isWeekend(): Boolean {
        val cal: Calendar = GregorianCalendar()
        cal.time = Date()
        val day: Int = cal.get(Calendar.DAY_OF_WEEK)
        return day == Calendar.SUNDAY || day == Calendar.SATURDAY
    }

    @JvmStatic
    @Throws(NoSuchAlgorithmException::class)
    fun encryptSHA512(target: String): String {
        val messageDigest =
            MessageDigest.getInstance("SHA-512")
        val encryptedPassword = StringBuilder()
        messageDigest.update(target.toByteArray())
        val buffer = messageDigest.digest()
        for (temp in buffer) {
            var sb =
                StringBuilder(Integer.toHexString(temp.toInt()))
            while (sb.length < 2) {
                sb.insert(0, "0")
            }
            sb = StringBuilder(sb.substring(sb.length - 2))
            encryptedPassword.append(sb)
        }
        return encryptedPassword.toString()
    }

    fun convertErrorBody(throwable: HttpException): String {
        return try {
            val errorBody = JSONObject(throwable.response()?.errorBody()!!.string())
            errorBody.getString("message")
        } catch (e: Exception) {
            "알 수 없는 오류가 발생했습니다. 잠시만 기다려주세요."
        }
    }

    const val NETWORK_ERROR_MESSAGE = "서버에 도달할 수 없습니다. 네트워크 상태를 확인해 주세요."

    fun String.getUrlFileName(): String {
        val fileName: String = this.substring(this.lastIndexOf('/') + 1, this.length)
        return fileName.substring(0, fileName.lastIndexOf('.'))
    }

    fun String.getUrlExtension(): String {
        val fileName: String = this.substring(this.lastIndexOf('/') + 1, this.length)
        return fileName.substring(fileName.lastIndexOf('.') + 1)
    }
}
