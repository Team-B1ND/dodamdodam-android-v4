package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.network.api.BusApi
import javax.inject.Inject

class BusRemote @Inject constructor(
    val api: BusApi
) {
}