package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.write

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import javax.inject.Inject

@HiltViewModel
class LostFoundWriteViewModel @Inject constructor(
    private val useCases : LostFoundUseCases
) : BaseViewModel(){
    val isLost = MutableLiveData<Boolean>()
    val isFound = MutableLiveData<Boolean>()
    val title = MutableLiveData<String>()
    val place = MutableLiveData<String>()
    val content = MutableLiveData<String>()
}
