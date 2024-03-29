package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetLostFound @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : UseCase<GetLostFound.Params, List<LostFound>>() {

    data class Params(
        val page: String,
        val type: String
    )

    override fun invoke(params: Params): Flow<Resource<List<LostFound>>> = execute {
        Log.d("LostFoundUseCase", "실행")
        lostFoundRepository.getLostFound(params.page, params.type)
    }
}
