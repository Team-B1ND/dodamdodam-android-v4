package kr.hs.dgsw.smartschool.data.util.parceler

import android.os.Parcel
import kotlinx.parcelize.Parceler
import kr.hs.dgsw.smartschool.data.network.response.member.StudentIdResponse

object StudentIdParceler : Parceler<StudentIdResponse> {

    override fun create(parcel: Parcel) = StudentIdResponse(parcel.readInt())

    override fun StudentIdResponse.write(parcel: Parcel, flags: Int) =
        parcel.writeInt(id)
}
