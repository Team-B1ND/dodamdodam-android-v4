package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus.DeleteBusUseCase
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val deleteBusApplyUseCase: DeleteBusUseCase
    private val getBusUse
): BaseViewModel() {
}
