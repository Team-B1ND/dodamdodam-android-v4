package kr.hs.dgsw.smartschool.dodamdodam.features.out

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.out.OutUseCases
import javax.inject.Inject

@HiltViewModel
class OutViewModel @Inject constructor(
    private val outUseCases: OutUseCases
) : BaseViewModel() {
    
}
