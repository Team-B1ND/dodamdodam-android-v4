package kr.hs.dgsw.smartschool.data.network.response.data

import com.google.gson.annotations.SerializedName

data class PointData<PointType> (
    @SerializedName("point")
    var point: PointType
)
