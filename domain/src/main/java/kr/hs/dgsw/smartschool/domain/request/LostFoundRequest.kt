package kr.hs.dgsw.smartschool.domain.request

import kr.hs.dgsw.smartschool.domain.model.lostfound.Picture


data class LostFoundRequest(
    val idx: Int?,
    val type: Int,
    val title: String,
    val place: String?,
    val picture: List<Picture>?,
    val content: String
) {

    constructor(type: Int, title: String, place: String?, picture: List<Picture>?, content: String) :
            this(null, type, title, place, picture, content)
}