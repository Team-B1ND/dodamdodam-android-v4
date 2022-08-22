package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.PlaceApi
import kr.hs.dgsw.smartschool.domain.model.place.Place

class PlaceRemote(override val api: PlaceApi) : BaseRemote<PlaceApi>() {
    suspend fun getAllPlace(): List<Place> =
        api.getAllPlace().data.places
}
