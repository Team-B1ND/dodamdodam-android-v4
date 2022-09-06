package kr.hs.dgsw.smartschool.data.network.response.data

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus

data class OutData(
    @field:SerializedName("outgoingList") val outGoingList: List<OutItem>,
    @field:SerializedName("outsleepingList") val outSleepingList: List<OutItem>
) {
    constructor(): this(emptyList(), emptyList())

    fun getAll(): List<OutItem> {
        val list = ArrayList<OutItem>()

        list.addAll(outSleepingList)
        list.addAll(outGoingList)
        return list.sortedBy { it.startOutDate }.filter { !it.isPassTime() }
    }

    fun getAllows(): List<OutItem> {
        val allOutItem = getAll()
        return allOutItem.filter { it.status == OutStatus.ALLOWED }
    }
}