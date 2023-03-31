package kr.hs.dgsw.smartschool.data.network.response.song.melon

data class SongChartResponse(
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
