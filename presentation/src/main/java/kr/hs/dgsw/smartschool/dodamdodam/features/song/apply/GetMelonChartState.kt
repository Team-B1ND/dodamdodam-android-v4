package kr.hs.dgsw.smartschool.dodamdodam.features.song.apply

import kr.hs.dgsw.smartschool.domain.model.song.MelonChart

data class GetMelonChartState(
    val melonChartList: List<MelonChart> = emptyList(),
    val error: String = ""
)
