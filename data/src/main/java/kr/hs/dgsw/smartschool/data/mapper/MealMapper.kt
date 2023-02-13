package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity
import kr.hs.dgsw.smartschool.domain.model.meal.Meal

class MealMapper : BaseEntityMapper<Meal, MealEntity> {
    override fun mapToModel(entity: MealEntity): Meal {
        return Meal(
            breakfast = entity.breakfast,
            date = "${entity.year}-${entity.month}-${entity.day}",
            dinner = entity.dinner,
            exists = entity.exists,
            lunch = entity.lunch
        )
    }

    override fun mapToEntity(model: Meal): MealEntity {
        return MealEntity(
            year = model.date.split("-")[0].toInt(),
            month = model.date.split("-")[1].toInt(),
            day = model.date.split("-")[2].toInt(),
            exists = model.exists,
            breakfast = model.safeBreakfast,
            lunch = model.safeLunch,
            dinner = model.safeDinner
        )
    }
}

fun MealEntity.toModel(): Meal = Meal(
    date = "${this.year}-${this.month}-${this.day}",
    exists = this.exists,
    breakfast = this.breakfast,
    lunch = this.lunch,
    dinner = this.dinner
)
