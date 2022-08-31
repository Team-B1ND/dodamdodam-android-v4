package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.MemberApi
import kr.hs.dgsw.smartschool.data.network.response.data.MemberData
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.Parent
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.model.member.Teacher
import kr.hs.dgsw.smartschool.domain.request.ModifyMemberInfoRequest
import kr.hs.dgsw.smartschool.domain.request.ModifyPwRequest
import javax.inject.Inject

class MemberRemote @Inject constructor(
    override val api: MemberApi
) : BaseRemote<MemberApi>() {

    suspend fun getMembers(): MemberData =
        api.getMembers().data

    suspend fun changeMemberInfo(request: ModifyMemberInfoRequest): String =
        api.modifyMemberInfo(request).message

    suspend fun deleteMember(memberId: String): String =
        api.deleteMember(memberId).message

    suspend fun getMyInfo(): Student =
        api.getMyInfo().data

    suspend fun getParents(): List<Parent> =
        api.getParents().data

    suspend fun modifyPw(request: ModifyPwRequest): String =
        api.modifyPw(request).message

    suspend fun getMember(memberId: String): Member =
        api.getMember(memberId).data

    suspend fun getStudents(): List<Student> =
        api.getStudents().data

    suspend fun getTeachers(): List<Teacher> =
        api.getTeachers().data
}
