package kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus

import io.reactivex.Completable
import kr.hs.dgsw.b1nd.dodamdodam.domain.base.ParamsUseCase
import kr.hs.dgsw.b1nd.dodamdodam.domain.repository.BusRepository
import kr.hs.dgsw.b1nd.dodamdodam.domain.request.PostBusRequest
import javax.inject.Inject

class PostBusApplyUseCase @Inject constructor(
    private val busRepository: BusRepository
) : ParamsUseCase<PostBusApplyUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return busRepository.postBusApply(PostBusRequest(params.busIdx))
    }

    data class Params(val busIdx: String)
}