package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class HideLostFound @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : BaseUseCase<HideLostFound.Params, String >() {


    data class Params(
        val lostFound: LostFound
    )

    override fun invoke(params: Params): Flow<Resource<String>> = execute{
        lostFoundRepository.hideLostFound(params.lostFound)
    }
}