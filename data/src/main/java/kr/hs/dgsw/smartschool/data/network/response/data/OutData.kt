package kr.hs.dgsw.smartschool.data.network.response.data

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.out.OutGoing
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutSleeping

data class OutData(
    @field:SerializedName("leave")
    val outSleeping: List<OutSleeping>,
    @field:SerializedName("pass")
    val outGoing: List<OutGoing>
) {
    fun getAll(): List<OutItem> {
        val list = ArrayList<OutItem>()

        for (data in outSleeping)
            data.isAllow = data.isAllowTeacher

        list.addAll(outSleeping)
        list.addAll(outGoing)
        return list.sortedBy { it.startTime }.filter { !it.isPassTime() }
    }
}