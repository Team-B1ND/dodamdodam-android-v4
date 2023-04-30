package kr.hs.dgsw.smartschool.data.util.parceler

import android.os.Parcel
import kotlinx.parcelize.Parceler
import kr.hs.dgsw.smartschool.data.network.response.member.TeacherIdResponse

object TeacherIdParceler : Parceler<TeacherIdResponse?> {

    override fun create(parcel: Parcel) = TeacherIdResponse(parcel.readString() ?: "")

    override fun TeacherIdResponse?.write(parcel: Parcel, flags: Int) =
        parcel.writeString(this?.id)
}
