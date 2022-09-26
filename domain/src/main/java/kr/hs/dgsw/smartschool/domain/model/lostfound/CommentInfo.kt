package kr.hs.dgsw.smartschool.domain.model.lostfound

data class CommentInfo(
    val idx : Int,
    val img : String = "",
    val name : String = "",
    val uploadTime : String = "",
    val content : String = "",
    val isMine : Boolean
)
