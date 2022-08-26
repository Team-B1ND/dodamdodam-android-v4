package kr.hs.dgsw.smartschool.domain.model.lostfound

data class LostFoundData(
    val pageData: List<LostFound>,
    val result: List<LostFound>,
    val comments: List<LostFoundComment>
)
