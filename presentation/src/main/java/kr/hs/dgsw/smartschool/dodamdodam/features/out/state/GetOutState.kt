package kr.hs.dgsw.smartschool.dodamdodam.features.out.state

import kr.hs.dgsw.smartschool.domain.model.out.OutItem

data class GetOutState(
    val outList: List<OutItem> = emptyList(),
    val isEmptyList: Boolean = false,
    val error: String = ""
)
