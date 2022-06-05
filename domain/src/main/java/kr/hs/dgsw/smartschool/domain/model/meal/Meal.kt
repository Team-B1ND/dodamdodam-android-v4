package kr.hs.dgsw.smartschool.domain.model.meal

data class Meal (
    var breakfast: String,
    val date: String,
    var dinner: String,
    val exists: Boolean,
    var lunch: String
)
