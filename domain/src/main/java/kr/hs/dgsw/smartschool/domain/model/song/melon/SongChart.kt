package kr.hs.dgsw.smartschool.domain.model.song.melon

data class SongChart(
    val name: String,
    val album: String,
    val artist: String,
    val thumbnail: String,
    val rank: String
) {
    val showingRank: String
        get() {
            return "#" + rank + "위"
        }
}
