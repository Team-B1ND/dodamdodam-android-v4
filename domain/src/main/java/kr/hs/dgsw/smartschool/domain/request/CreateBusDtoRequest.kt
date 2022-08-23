package kr.hs.dgsw.smartschool.domain.request

data class CreateBusDtoRequest(
    val busName: String,
    val description : String,
    val leaveTime : String,
    val timeRequired : String,
    val peopleLimit : Int
)
