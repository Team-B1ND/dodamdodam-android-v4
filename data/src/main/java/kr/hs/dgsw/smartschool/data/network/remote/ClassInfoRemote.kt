package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.ClassInfoApi
import kr.hs.dgsw.smartschool.data.network.response.classroom.ClassroomResponse

class ClassInfoRemote(override val api: ClassInfoApi) : BaseRemote<ClassInfoApi>() {
    suspend fun getClassInfo(): List<ClassroomResponse> =
        api.getClassRooms().data
}
