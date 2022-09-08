package kr.hs.dgsw.smartschool.dodamdodam.features.out.detail

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.out.OutUseCases
import javax.inject.Inject

@HiltViewModel
class OutDetailViewModel @Inject constructor(
    private val outUseCases: OutUseCases
) : BaseViewModel() {

    val startTime = MutableLiveData<String>()
    val endDate = MutableLiveData<String>()
    val startDate = MutableLiveData<String>()

    val endTime = MutableLiveData<String>()
    val reason = MutableLiveData<String>()

    val isAllow = MutableLiveData<Boolean>()
    val isOutSleeping = MutableLiveData<Boolean>()

    fun onClickUpdate() {

    }

}