package kr.hs.dgsw.smartschool.domain.model.lostfound

import com.google.gson.annotations.SerializedName
import java.io.Serializable

enum class LostFoundType (val value: Int) : Serializable {
    @SerializedName("0")
    FOUND(0),
    @SerializedName("1")
    LOST(1);

    fun getString(): String =
        if (value == LOST.value) "분실물" else "습득물"

    fun getChangedType(): LostFoundType =
        if (value == LOST.value) FOUND else LOST
}