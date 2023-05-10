package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.place.PlaceResponse
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import retrofit2.http.GET

interface PlaceApi {
    @GET(DodamUrl.PLACE)
    suspend fun getPlaces(): Response<List<PlaceResponse>>

    @GET(DodamUrl.Place.DORMITORY)
    suspend fun getDormitoryPlaces(): Response<List<PlaceResponse>>
}
