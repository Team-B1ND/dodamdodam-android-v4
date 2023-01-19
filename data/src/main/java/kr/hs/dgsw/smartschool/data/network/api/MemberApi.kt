package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.MemberData
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.Parent
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.model.member.Teacher
import kr.hs.dgsw.smartschool.domain.request.auth.ModifyPwRequest
import kr.hs.dgsw.smartschool.domain.request.member.ModifyMemberInfoRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface MemberApi {

    @GET(DodamUrl.MEMBER)
    suspend fun getMembers(): Response<MemberData>

    @PATCH(DodamUrl.MEMBER)
    suspend fun modifyMemberInfo(
        @Body modifyMemberInfoRequest: ModifyMemberInfoRequest
    ): Response<Any>

    @DELETE(DodamUrl.Member.MEMBER_ID)
    suspend fun deleteMember(
        @Path("memberId") memberId: String
    ): Response<Any>

    @GET(DodamUrl.Member.MY)
    suspend fun getMyInfo(): Response<Student>

    @GET(DodamUrl.Member.PARENT)
    suspend fun getParents(): Response<List<Parent>>

    @PATCH(DodamUrl.Member.PW)
    suspend fun modifyPw(
        @Body modifyPwRequest: ModifyPwRequest
    ): Response<Any>

    @GET(DodamUrl.Member.Search.MEMBER_ID)
    suspend fun getMember(
        @Path("memberId") id: String
    ): Response<Member>

    @GET(DodamUrl.Member.STUDENT)
    suspend fun getStudents(): Response<List<Student>>

    @GET(DodamUrl.Member.TEACHER)
    suspend fun getTeachers(): Response<List<Teacher>>
}
