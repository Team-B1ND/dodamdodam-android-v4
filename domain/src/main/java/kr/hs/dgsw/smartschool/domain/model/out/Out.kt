package kr.hs.dgsw.smartschool.domain.model.out

import com.google.gson.annotations.SerializedName

data class Out(
    @field:SerializedName("outgoingList") val outGoingList: List<OutItem>,
    @field:SerializedName("outsleepingList") val outSleepingList: List<OutItem>
)