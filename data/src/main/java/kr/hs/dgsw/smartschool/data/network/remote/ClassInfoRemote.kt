package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.ClassInfoApi
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

class ClassInfoRemote(override val api: ClassInfoApi) : BaseRemote<ClassInfoApi>() {
    suspend fun getClassInfo(): List<Classroom> =
        api.getClassRooms().data
}
