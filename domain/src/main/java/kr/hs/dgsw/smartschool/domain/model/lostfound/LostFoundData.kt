package kr.hs.dgsw.smartschool.domain.model.lostfound

data class LostFoundData(
    val content: String = "내용을 가져오지 못함",
    val picture: String = "사진을 가져오지 못함",
    val place: String = "프로그래밍 1실",
    val title: String = "제목을 가져오지 못함",
    val type: String = "LOST"
)
