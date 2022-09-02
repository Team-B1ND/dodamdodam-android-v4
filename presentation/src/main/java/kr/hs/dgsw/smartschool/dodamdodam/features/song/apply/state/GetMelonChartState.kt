package kr.hs.dgsw.smartschool.dodamdodam.features.song.apply.state

import kr.hs.dgsw.smartschool.domain.model.song.melon.MelonChart

data class GetMelonChartState(
    val melonChartList: List<MelonChart> = emptyList(),
    val error: String = ""
)
