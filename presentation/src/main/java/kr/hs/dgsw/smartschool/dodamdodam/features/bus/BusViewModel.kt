package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    //private val busInfoUseCase : BusInfoUsecase
): BaseViewModel() {
    private val countPeople = MutableLiveData<String>()
}
