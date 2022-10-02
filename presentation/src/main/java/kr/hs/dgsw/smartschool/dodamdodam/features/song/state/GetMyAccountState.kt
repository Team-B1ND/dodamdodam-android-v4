package kr.hs.dgsw.smartschool.dodamdodam.features.song.state

import kr.hs.dgsw.smartschool.domain.model.account.Account

data class GetMyAccountState(
    val account: Account? = null,
    val error: String = ""
)
