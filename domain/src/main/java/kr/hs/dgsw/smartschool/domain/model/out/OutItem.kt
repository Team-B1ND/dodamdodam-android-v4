package kr.hs.dgsw.smartschool.domain.model.out

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

open class OutItem(
    @field:SerializedName("idx")
    val idx: Int,
    @field:SerializedName("studentIdx")
    val studentIdx: Int,
    @field:SerializedName("startTime")
    val startTime: Date,
    @field:SerializedName("endTime")
    val endTime: Date,
    @field:SerializedName("isAllow")
    var isAllow: Int,
    @field:SerializedName("reason")
    val reason: String,
    @field:SerializedName("allowTeacherIdx")
    val allowTeacherIdx: Int,
    @field:SerializedName("allowTeacherTime")
    val allowTeacherTime: Date,
    @field:SerializedName("createdAt")
    val createdAt: Date
) : Serializable {

    constructor() : this(0, 0, Date(), Date(), 0, "", 0, Date(), Date())

    override fun toString(): String {
        val format = SimpleDateFormat("HH-dd HH:mm (E)", Locale.getDefault())
        return "${format.format(startTime)} ~\n ${format.format(endTime)}"
    }

    fun isNow(): Boolean {
        val now = Date()
        return now.after(startTime) && now.before(endTime)
    }

    fun isPassTime(): Boolean {
        val now = Date()
        return now.after(endTime)
    }
}
