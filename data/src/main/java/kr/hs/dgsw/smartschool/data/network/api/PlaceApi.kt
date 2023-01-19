package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.model.place.Place
import retrofit2.http.GET

interface PlaceApi {
    @GET(DodamUrl.PLACE)
    suspend fun getPlaces(): Response<List<Place>>
}
