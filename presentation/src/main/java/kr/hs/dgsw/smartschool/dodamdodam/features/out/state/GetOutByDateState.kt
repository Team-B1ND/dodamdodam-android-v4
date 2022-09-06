package kr.hs.dgsw.smartschool.dodamdodam.features.out.state

import kr.hs.dgsw.smartschool.domain.model.out.OutItem

data class GetOutByDateState(
    val isUpdate: Boolean = false,
    val outList: List<OutItem> = emptyList(),
    val error: String = ""
)
