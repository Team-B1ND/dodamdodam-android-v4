package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.DefaultStudyRoomData
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.request.studyroom.DefaultStudyRoomByTypeRequest
import kr.hs.dgsw.smartschool.domain.request.studyroom.DefaultStudyRoomRequest
import kr.hs.dgsw.smartschool.domain.request.studyroom.StudyRoomRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StudyRoomApi {

    @POST("study-room")
    suspend fun applyStudyRoom(
        @Body request: StudyRoomRequest
    ): Response<Any>

    @POST("study-room")
    suspend fun modifyAppliedStudyRoom(
        @Body request: StudyRoomRequest
    ): Response<Any>

    @GET("study-room/{id}")
    suspend fun getStudyRoomById(
        @Path("id") id: Int
    ): Response<StudyRoom>

    @DELETE("study-room/{id}")
    suspend fun cancelStudyRoom(
        @Path("id") id: Int
    ): Response<Any>

    @GET("study-room/default")
    suspend fun getDefaultStudyRoom(): Response<DefaultStudyRoomData>

    @POST("study-room/default")
    suspend fun createDefaultStudyRoom(
        @Body request: DefaultStudyRoomRequest
    ): Response<Any>

    @POST("study-room/default/week-type")
    suspend fun createDefaultStudyRoomByWeekType(
        @Body request: DefaultStudyRoomByTypeRequest
    ): Response<Any>

    @GET("study-room/my")
    suspend fun getMyStudyRoom(): Response<List<StudyRoom?>>
}
