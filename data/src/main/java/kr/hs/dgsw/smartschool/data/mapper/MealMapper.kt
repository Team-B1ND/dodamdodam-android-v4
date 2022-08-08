package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import java.time.LocalDate

class MealMapper : BaseEntityMapper<Meal, MealEntity> {

    override fun mapToModel(entity: MealEntity): Meal {
        val date = LocalDate.of(entity.year, entity.month, entity.day)

        return Meal(
            entity.breakfast.toString(),
            date.toString(),
            entity.dinner.toString(),
            entity.exists,
            entity.lunch.toString()
        )
    }

    override fun mapToEntity(model: Meal): MealEntity {
        val (year, month, day) = model.date.split('-')
        return MealEntity(
            year.toInt(),
            month.toInt(),
            day.toInt(),
            model.exists,
            model.breakfast,
            model.lunch,
            model.dinner
        )
    }
}