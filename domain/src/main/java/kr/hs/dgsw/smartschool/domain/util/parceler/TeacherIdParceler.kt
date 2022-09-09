package kr.hs.dgsw.smartschool.domain.util.parceler

import android.os.Parcel
import kotlinx.parcelize.Parceler
import kr.hs.dgsw.smartschool.domain.model.member.StudentId
import kr.hs.dgsw.smartschool.domain.model.member.TeacherId
import java.util.Date

object TeacherIdParceler : Parceler<TeacherId> {

    override fun create(parcel: Parcel) = TeacherId(parcel.readInt())

    override fun TeacherId.write(parcel: Parcel, flags: Int)
            = parcel.writeInt(id)
}