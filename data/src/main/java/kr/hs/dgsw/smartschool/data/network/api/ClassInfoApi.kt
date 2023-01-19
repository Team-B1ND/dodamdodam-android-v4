package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import retrofit2.http.GET

interface ClassInfoApi {
    @GET(DodamUrl.CLASSROOM)
    suspend fun getClassRooms(): Response<List<Classroom>>
}
