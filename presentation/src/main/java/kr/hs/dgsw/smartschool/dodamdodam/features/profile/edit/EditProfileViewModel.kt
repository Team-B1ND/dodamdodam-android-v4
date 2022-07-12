package kr.hs.dgsw.smartschool.dodamdodam.features.profile.edit

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel

@HiltViewModel
class EditProfileViewModel : BaseViewModel() {

    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()



}