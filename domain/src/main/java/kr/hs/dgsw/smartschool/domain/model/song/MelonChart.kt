package kr.hs.dgsw.smartschool.domain.model.song

data class MelonChart(
    val title: String,
    val artist: String,
    val thumbnail: String,
    val rank: String
) {
    val showingRank = rank + "위"
}
