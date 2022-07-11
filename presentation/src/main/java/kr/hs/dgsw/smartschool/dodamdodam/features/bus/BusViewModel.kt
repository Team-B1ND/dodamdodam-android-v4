package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus.*
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.usecase.bus.BusUseCases
import java.security.PrivateKey
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busUseCases: BusUseCases
): BaseViewModel() {
    fun getBusList():List<Bus>(){
        
    }
}
