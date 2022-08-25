package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.MemberApi
import kr.hs.dgsw.smartschool.data.network.response.data.MemberData
import kr.hs.dgsw.smartschool.data.network.response.data.MyInfoData
import kr.hs.dgsw.smartschool.data.network.response.data.StudentData
import kr.hs.dgsw.smartschool.data.network.response.data.TeacherData
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.request.MyInfoChangeRequest
import javax.inject.Inject

class MemberRemote @Inject constructor(
    override val api: MemberApi
) : BaseRemote<MemberApi>() {

    suspend fun getAllMember(): MemberData =
        api.getAllMembers().data

    suspend fun getMember(id: String): Member =
        api.getMember(id).data

    suspend fun getMyInfo(): MyInfoData =
        api.getMyInfo().data

    suspend fun getAllStudent(): StudentData =
        api.getStudents().data

    suspend fun getAllTeacher(): TeacherData =
        api.getTeachers().data

    suspend fun changeMemberInfo(memberId: String, request: MyInfoChangeRequest) =
        api.changeMemberInfo(memberId, request)
}
