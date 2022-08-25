package kr.hs.dgsw.smartschool.domain.usecase.song

data class SongUseCases(
    val getAllowSong: GetAllowSong,
    val getMySong: GetMySong,
    val getPendingSong: GetPendingSong,
    val postSong: PostSong,
    val getMelonChart: GetMelonChart
)
