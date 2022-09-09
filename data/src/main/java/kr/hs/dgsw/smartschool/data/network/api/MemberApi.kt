package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.MemberData
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

    @GET("members")
    suspend fun getMembers(): Response<MemberData>

    @PATCH("members")
    suspend fun modifyMemberInfo(
        @Body modifyMemberInfoRequest: ModifyMemberInfoRequest
    ): Response<Any>

    @DELETE("/members/{memberId}")
    suspend fun deleteMember(
        @Path("memberId") memberId: String
    ): Response<Any>

    @GET("members/my")
    suspend fun getMyInfo(): Response<Student>

    @GET("members/parent")
    suspend fun getParents(): Response<List<Parent>>

    @PATCH("members/pw")
    suspend fun modifyPw(
        @Body modifyPwRequest: ModifyPwRequest
    ): Response<Any>

    @GET("members/search/{memberId}")
    suspend fun getMember(
        @Path("memberId") id: String
    ): Response<Member>

    @GET("members/student")
    suspend fun getStudents(): Response<List<Student>>

    @GET("members/teacher")
    suspend fun getTeachers(): Response<List<Teacher>>
}
