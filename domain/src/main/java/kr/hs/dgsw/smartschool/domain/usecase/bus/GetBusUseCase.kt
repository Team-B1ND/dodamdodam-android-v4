package kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus

import io.reactivex.Single
import kr.hs.dgsw.b1nd.dodamdodam.domain.base.BaseUseCase
import kr.hs.dgsw.b1nd.dodamdodam.domain.model.bus.BusWithDate
import kr.hs.dgsw.b1nd.dodamdodam.domain.repository.BusRepository
import javax.inject.Inject

class GetBusUseCase @Inject constructor(
    private val busRepository: BusRepository
) : BaseUseCase<Single<List<BusWithDate>>>() {

    override fun buildUseCaseObservable(): Single<List<BusWithDate>> {
        return busRepository.getCurrentBus()
    }
}