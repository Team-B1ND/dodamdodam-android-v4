package kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus

import io.reactivex.Completable
import kr.hs.dgsw.b1nd.dodamdodam.domain.base.ParamsUseCase
import kr.hs.dgsw.b1nd.dodamdodam.domain.repository.BusRepository
import kr.hs.dgsw.b1nd.dodamdodam.domain.request.BusRequest
import javax.inject.Inject

class PutBusApplyUseCase @Inject constructor(
    private val busRepository: BusRepository
) : ParamsUseCase<PutBusApplyUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return busRepository.putBustApply(BusRequest(params.busIdx, params.originBusIdx))
    }

    data class Params(
        val busIdx: String,
        val originBusIdx: String
    )
}