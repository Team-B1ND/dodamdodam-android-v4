package kr.hs.dgsw.smartschool.domain.util.parceler

import android.os.Parcel
import kotlinx.parcelize.Parceler
import kr.hs.dgsw.smartschool.domain.model.member.StudentId
import java.util.Date

object StudentIdParceler : Parceler<StudentId> {

    override fun create(parcel: Parcel) = StudentId(parcel.readInt())

    override fun StudentId.write(parcel: Parcel, flags: Int)
            = parcel.writeInt(id)
}