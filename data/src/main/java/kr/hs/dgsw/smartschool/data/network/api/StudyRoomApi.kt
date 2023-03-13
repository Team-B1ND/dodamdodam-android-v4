package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.DefaultStudyRoomData
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.param.studyroom.DefaultStudyRoomByTypeRequest
import kr.hs.dgsw.smartschool.domain.param.studyroom.DefaultStudyRoomRequest
import kr.hs.dgsw.smartschool.domain.param.studyroom.StudyRoomRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StudyRoomApi {

    @POST(DodamUrl.STUDY_ROOM)
    suspend fun applyStudyRoom(
        @Body request: StudyRoomRequest
    ): Response<Any>

    @POST(DodamUrl.STUDY_ROOM)
    suspend fun modifyAppliedStudyRoom(
        @Body request: StudyRoomRequest
    ): Response<Any>

    @GET(DodamUrl.StudyRoom.ID)
    suspend fun getStudyRoomById(
        @Path("id") id: Int
    ): Response<StudyRoom>

    @DELETE(DodamUrl.StudyRoom.ID)
    suspend fun cancelStudyRoom(
        @Path("id") id: Int
    ): Response<Any>

    @GET(DodamUrl.StudyRoom.DEFAULT)
    suspend fun getDefaultStudyRoom(): Response<DefaultStudyRoomData>

    @POST(DodamUrl.StudyRoom.DEFAULT)
    suspend fun createDefaultStudyRoom(
        @Body request: DefaultStudyRoomRequest
    ): Response<Any>

    @POST(DodamUrl.StudyRoom.Default.WEEK_TYPE)
    suspend fun createDefaultStudyRoomByWeekType(
        @Body request: DefaultStudyRoomByTypeRequest
    ): Response<Any>

    @GET(DodamUrl.StudyRoom.MY)
    suspend fun getMyStudyRoom(): Response<List<StudyRoom?>>
}
