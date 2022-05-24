package kr.hs.dgsw.smartschool.domain.request

import android.hardware.SensorAdditionalInfo

data class SignUpRequest(
    val id:String,
    val pw: String,
    val name: String,
    val phone: String,
    val email:String,
    val accountType:String,
    val additionalInfo: SensorAdditionalInfo
)
