package kr.hs.dgsw.smartschool.dodamschool.ui.features.sign.signin

import kr.hs.dgsw.smartschool.data.database.entity.SignInEntity
import kr.hs.dgsw.smartschool.domain.model.response.SignIn

data class AutoSignInState(
    val isLoading: Boolean = false,
    val data: SignIn? = null,
    val error: String = ""
)