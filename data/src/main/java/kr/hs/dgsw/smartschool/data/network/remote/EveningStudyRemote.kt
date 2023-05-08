package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.EveningStudyApi
import kr.hs.dgsw.smartschool.data.network.request.nightstudy.EveningStudyRequest
import kr.hs.dgsw.smartschool.data.network.response.eveningstudy.EveningStudyResponse
import javax.inject.Inject

class EveningStudyRemote @Inject constructor(
    override val api: EveningStudyApi
) : BaseRemote<EveningStudyApi>() {
    suspend fun applyEveningStudy(request: EveningStudyRequest): EveningStudyResponse = api.applyEveningStudy(request).data
    suspend fun deleteEveningStudy(eveningStudyId: Int): String = api.deleteEveningStudy(eveningStudyId).message
    suspend fun getMyEveningStudy(): List<EveningStudyResponse> = api.getMyEveningStudy().data
}
