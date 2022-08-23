package kr.hs.dgsw.smartschool.domain.model.out

import com.google.gson.annotations.SerializedName
import java.util.Date

data class OutSleeping(
    @field:SerializedName("allowParentIdx")
    val allowParentIdx: Int,
    @field:SerializedName("allowParentTime")
    val allowParentTime: Date,
    @field:SerializedName("arrivedTime")
    val arrivedTime: Date,
    @field:SerializedName("isAllowTeacher")
    val isAllowTeacher: Int
): OutItem()
