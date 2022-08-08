package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.ClassInfoData
import retrofit2.http.GET

interface ClassInfoApi {
    @GET("classroom")
    suspend fun getAllClass(): Response<ClassInfoData>
}