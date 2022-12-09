package kr.hs.dgsw.smartschool.domain.util.parceler

import android.os.Parcel
import kotlinx.parcelize.Parceler
import kr.hs.dgsw.smartschool.domain.model.member.TeacherId

object TeacherIdParceler : Parceler<TeacherId> {

    override fun create(parcel: Parcel) = TeacherId(parcel.readString() ?: "")

    override fun TeacherId.write(parcel: Parcel, flags: Int) =
        parcel.writeString(id)
}
