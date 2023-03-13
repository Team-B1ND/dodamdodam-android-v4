package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.MemberApi
import kr.hs.dgsw.smartschool.data.network.response.data.MemberData
import kr.hs.dgsw.smartschool.data.network.response.member.MemberResponse
import kr.hs.dgsw.smartschool.data.network.response.member.ParentResponse
import kr.hs.dgsw.smartschool.data.network.response.member.StudentResponse
import kr.hs.dgsw.smartschool.data.network.response.member.TeacherResponse
import kr.hs.dgsw.smartschool.domain.param.auth.ModifyPwRequest
import kr.hs.dgsw.smartschool.domain.param.member.ModifyMemberInfoRequest
import javax.inject.Inject

class MemberRemote @Inject constructor(
    override val api: MemberApi
) : BaseRemote<MemberApi>() {

    suspend fun getMembers(): MemberData =
        api.getMembers().data

    suspend fun modifyMemberInfo(request: ModifyMemberInfoRequest): String =
        api.modifyMemberInfo(request).message

    suspend fun deleteMember(memberId: String): String =
        api.deleteMember(memberId).message

    suspend fun getMyInfo(): StudentResponse =
        api.getMyInfo().data

    suspend fun getParents(): List<ParentResponse> =
        api.getParents().data

    suspend fun modifyPw(request: ModifyPwRequest): String =
        api.modifyPw(request).message

    suspend fun getMember(memberId: String): MemberResponse =
        api.getMember(memberId).data

    suspend fun getStudents(): List<StudentResponse> =
        api.getStudents().data

    suspend fun getTeachers(): List<TeacherResponse> =
        api.getTeachers().data
}
