package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.MemberData
import kr.hs.dgsw.smartschool.data.network.response.member.MemberResponse
import kr.hs.dgsw.smartschool.data.network.response.member.ParentResponse
import kr.hs.dgsw.smartschool.data.network.response.member.StudentResponse
import kr.hs.dgsw.smartschool.data.network.response.member.TeacherResponse
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.param.auth.ModifyPwRequest
import kr.hs.dgsw.smartschool.domain.param.member.ModifyMemberInfoRequest
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
    suspend fun getMyInfo(): Response<StudentResponse>

    @GET(DodamUrl.Member.PARENT)
    suspend fun getParents(): Response<List<ParentResponse>>

    @PATCH(DodamUrl.Member.PW)
    suspend fun modifyPw(
        @Body modifyPwRequest: ModifyPwRequest
    ): Response<Any>

    @GET(DodamUrl.Member.Search.MEMBER_ID)
    suspend fun getMember(
        @Path("memberId") id: String
    ): Response<MemberResponse>

    @GET(DodamUrl.Member.STUDENT)
    suspend fun getStudents(): Response<List<StudentResponse>>

    @GET(DodamUrl.Member.TEACHER)
    suspend fun getTeachers(): Response<List<TeacherResponse>>
}
