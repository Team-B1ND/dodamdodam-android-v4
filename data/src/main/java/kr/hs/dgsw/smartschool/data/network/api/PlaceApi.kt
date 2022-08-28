package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.PlaceData
import retrofit2.http.GET

interface PlaceApi {
    @GET("place")
    suspend fun getPlaces(): Response<PlaceData>
}
