package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.MemberData
import kr.hs.dgsw.smartschool.data.network.response.data.MyInfoData
import kr.hs.dgsw.smartschool.data.network.response.data.StudentData
import kr.hs.dgsw.smartschool.data.network.response.data.TeacherData
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.request.MyInfoChangeRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface MemberApi {

    @GET("members")
    suspend fun getAllMembers(
        @Query("allowed") allowed: Int
    ): Response<MemberData>

    @GET("members/search")
    suspend fun getMember(
        @Query("id") id: String
    ): Response<Member>

    @GET("members/my")
    suspend fun getMyInfo(): Response<MyInfoData>

    @GET("members/student")
    suspend fun getStudents(): Response<StudentData>

    @GET("members/teacher")
    suspend fun getTeachers(): Response<TeacherData>

    @PUT("members")
    suspend fun changeMemberInfo(
        @Query("memberId") memberId: String,
        @Body myInfoChangeRequest: MyInfoChangeRequest
    )
}
