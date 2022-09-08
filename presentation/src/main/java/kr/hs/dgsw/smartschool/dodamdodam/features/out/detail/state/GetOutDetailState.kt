package kr.hs.dgsw.smartschool.dodamdodam.features.out.detail.state

import kr.hs.dgsw.smartschool.domain.model.out.OutItem

data class GetOutDetailState(
    val outItem: OutItem? = null,
    val error: String = ""
)
