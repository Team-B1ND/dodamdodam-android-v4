package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.ClassInfoApi
import kr.hs.dgsw.smartschool.domain.model.classinfo.ClassInfo

class ClassInfoRemote(override val api: ClassInfoApi) : BaseRemote<ClassInfoApi>() {
    suspend fun getClassInfo(): List<ClassInfo> =
        api.getAllClass().data.classrooms
}