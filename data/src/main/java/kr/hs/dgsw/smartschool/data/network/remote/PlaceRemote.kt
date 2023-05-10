package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.PlaceApi
import kr.hs.dgsw.smartschool.data.network.response.place.PlaceResponse

class PlaceRemote(override val api: PlaceApi) : BaseRemote<PlaceApi>() {

    suspend fun getAllPlace(): List<PlaceResponse> = api.getPlaces().data

    suspend fun getDormitoryPlace(): List<PlaceResponse> = api.getDormitoryPlaces().data
}
