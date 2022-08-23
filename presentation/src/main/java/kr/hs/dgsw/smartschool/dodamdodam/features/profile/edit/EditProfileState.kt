package kr.hs.dgsw.smartschool.dodamdodam.features.profile.edit

data class EditProfileState(
    val isLoading: Boolean = false,
    val message: String = "",
    val error: String = ""
)
