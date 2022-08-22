package kr.hs.dgsw.smartschool.domain.model.member

import com.google.gson.annotations.SerializedName

enum class MemberAllowedType(val value: Int) {
    @SerializedName("0") WAITE(0),
    @SerializedName("1") ALLOW(1);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}
