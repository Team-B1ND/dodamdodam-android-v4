package kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus

import io.reactivex.Single
import kr.hs.dgsw.b1nd.dodamdodam.domain.base.BaseUseCase
import kr.hs.dgsw.b1nd.dodamdodam.domain.model.bus.Bus
import kr.hs.dgsw.b1nd.dodamdodam.domain.repository.BusRepository
import javax.inject.Inject

class GetMyBusApplyUseCase @Inject constructor(
    private val busRepository: BusRepository
) : BaseUseCase<Single<List<Bus>>>() {

    override fun buildUseCaseObservable(): Single<List<Bus>> {
        return busRepository.getMyBusApply()
    }
}