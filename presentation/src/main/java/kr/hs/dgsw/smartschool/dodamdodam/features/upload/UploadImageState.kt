package kr.hs.dgsw.smartschool.dodamdodam.features.upload

import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture

data class UploadImageState(
    val isLoading: Boolean = false,
    val picture: Picture? = null,
    val error: String = ""
)
