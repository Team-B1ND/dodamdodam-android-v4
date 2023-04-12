package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.classroom.ClassroomResponse
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import retrofit2.http.GET

interface ClassInfoApi {
    @GET(DodamUrl.ClassRoom.GET_CLASSROOM)
    suspend fun getClassRooms(): Response<List<ClassroomResponse>>
}
