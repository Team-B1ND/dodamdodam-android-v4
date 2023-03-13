package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.database.entity.MealEntity
import kr.hs.dgsw.smartschool.data.network.response.Meal.MealResponse
import kr.hs.dgsw.smartschool.domain.model.meal.Meal

fun MealEntity.toModel(): MealResponse = MealResponse(
    date = "${this.year}-${this.month}-${this.day}",
    exists = this.exists,
    breakfast = this.breakfast,
    lunch = this.lunch,
    dinner = this.dinner
)

fun MealResponse.toModel(): Meal = Meal(
    breakfast = this.breakfast,
    date = this.date,
    dinner = this.dinner,
    exists = this.exists,
    lunch = this.lunch
)
fun MealResponse.toEntity(): MealEntity = MealEntity(
    year = this.date.split("-")[0].toInt(),
    month = this.date.split("-")[1].toInt(),
    day = this.date.split("-")[2].toInt(),
    exists = this.exists,
    breakfast = this.safeBreakfast,
    lunch = this.safeLunch,
    dinner = this.safeDinner
)
