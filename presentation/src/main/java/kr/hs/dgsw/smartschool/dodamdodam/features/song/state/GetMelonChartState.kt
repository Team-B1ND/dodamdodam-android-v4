package kr.hs.dgsw.smartschool.dodamdodam.features.song.state

import kr.hs.dgsw.smartschool.domain.model.song.melon.SongChart

data class GetMelonChartState(
    val songChartList: List<SongChart> = emptyList(),
    val error: String = ""
)
